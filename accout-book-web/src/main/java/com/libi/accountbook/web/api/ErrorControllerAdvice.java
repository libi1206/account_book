package com.libi.accountbook.web.api;

import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.exception.AttrNotLoginUserDto;
import com.libi.accountbook.dto.exception.ParamNotFindDto;
import com.libi.accountbook.dto.exception.base.BaseExceptionDto;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.NoMoneyException;
import com.libi.accountbook.exception.ParamNotFindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static com.libi.accountbook.web.constant.UrlConst.ERROR_ROOT;

/**
 * @author libi
 */
@RestControllerAdvice
public class ErrorControllerAdvice implements ErrorController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = ParamNotFindException.class)
    public ResponseDto paramErrorHandler(ParamNotFindException exception) {
        return new ResponseDto(10001, "未找到必要的参数！", new ParamNotFindDto(exception));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseDto notFindErrorHandler(HttpServletRequest request) {
        logger.warn("访问404,uri:"+request.getRequestURI());
        return new ResponseDto(10003, "找不到对应的响应，可能是请求的url有误", new BaseExceptionDto(request.getRequestURI(),System.currentTimeMillis()));
    }

    @ExceptionHandler(value = AttrNotLoginUserException.class)
    public ResponseDto attrNotLoginUserErrorHandler(AttrNotLoginUserException e, HttpServletRequest request) {
        e.setUri(request.getRequestURI());
        e.setCurrentTime(System.currentTimeMillis());
        return new ResponseDto(10002, "无权修改或删除这个属性或这个属性不存在", new AttrNotLoginUserDto(e));
    }

    @ExceptionHandler(value = NoMoneyException.class)
    public ResponseDto noMoneyErrorHandler(NoMoneyException e, HttpServletRequest request) {
        e.setUri(request.getRequestURI());
        e.setCurrentTime(System.currentTimeMillis());
        return new ResponseDto(10002, "资产或小金库里的余额不足", new BaseExceptionDto(e));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseDto unknownErrorHandler(Exception e,HttpServletRequest request) {
        logger.warn("发生异常,uri:"+request.getRequestURI()+"异常："+e.getClass().getName()+":"+e.getMessage());
        e.printStackTrace();
        return new ResponseDto(10004, "服务器内部出错，请与我联系    className:"+e.getClass().getName(), new BaseExceptionDto(request.getRequestURI(), System.currentTimeMillis()));
    }


    @Override
    public String getErrorPath() {
        return ERROR_ROOT;
    }
}
