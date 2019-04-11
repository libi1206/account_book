package com.libi.accountbook.web.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.exception.TokenNotFindException;
import com.libi.accountbook.service.UserService;
import com.libi.accountbook.web.anno.RedisTransaction;
import com.libi.accountbook.web.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author libi
 * 切面的配置
 */
@Aspect
@Configuration
@PropertySource("classpath:app-param.properties")
public class AopConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisUtil redisUtil;
    @Reference
    private UserService userService;
    @Value("${cookie-timeout}")
    private Long cookieTimeout;

    /**
     * 配置需要检查token的切面
     */
    @Pointcut("execution (* com.libi.accountbook.web.api.*.*(..)) " +
            "&& args(..,request,response) " +
            "&& @annotation(com.libi.accountbook.web.anno.CheckToken)")
    public void checkTokenPointcut(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 需要开启Redis事务的切面
     */
    @Pointcut("@annotation(com.libi.accountbook.web.anno.RedisTransaction)")
    public void redisTransactionPointcut() {

    }

    /**
     * 检测Token的操作
     */
    @Before("checkTokenPointcut(request,response)")
    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws TokenNotFindException {
        logger.info("进入对比token环节，uri:" + request.getRequestURI());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUser user = userService.selectUserByUserName(userDetails.getUsername());
        String userId = user.getId().toString();
        String token = redisUtil.getString(userId);
        if (token == null) {
            logger.warn("没有在Redis里找到id为"+userId+"的Token");
            throw new TokenNotFindException(request.getRequestURI(), System.currentTimeMillis(), "服务器中没有对应的Token或已经失效，请重新登陆");
        }
        Cookie[] cookies = request.getCookies();
        boolean find = false;
        for (Cookie cookie : cookies) {
            //在传回的一大堆cookies里找token
            if ("TokenLogin".equals(cookie.getName())) {
                find = true;
                String requestToken = cookie.getValue();
                if (!token.equals(requestToken)) {
                    throw new TokenNotFindException(request.getRequestURI(), System.currentTimeMillis(), "Token对比失败，请重新登陆");
                } else {
                    //对比成功，给cookie延长时间
                    logger.info("比对成功！");
                    cookie.setMaxAge(Math.toIntExact(cookieTimeout));
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        if (!find) {
            throw new TokenNotFindException(request.getRequestURI(), System.currentTimeMillis(), "没有在请求的Cookie里找到token，请不要禁用Cookie或重新登陆");
        }
    }


    @Around("redisTransactionPointcut()")
    public Object redisTransaction(ProceedingJoinPoint joinPoint) {
        try {
            logger.info("开始Redis事务,Name:" + joinPoint.getTarget().getClass().getName());
            redisUtil.begin();
            Object o = joinPoint.proceed();
            logger.info("提交Redis事务");
            redisUtil.exec();
            return o;
        } catch (Throwable throwable) {
            logger.info("出现异常，回滚Redis事务 Name:"+throwable.getClass().getName());
            redisUtil.discard();
            throwable.printStackTrace();
            throw new RuntimeException("Redis事务出现异常");
        }
    }
}
