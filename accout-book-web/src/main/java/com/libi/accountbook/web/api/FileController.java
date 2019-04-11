package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.UserService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 */
@RestController
@RequestMapping(FILE_ROOT)
@PropertySource("classpath:app-param.properties")
public class FileController extends BaseController {
    @Reference
    private UserService userService;
    @Value("${head-img-nginx-path}")
    private String imageFilePath;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 上传用户发来的文件
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/imgUpload")
    @CheckToken
    public ResponseDto fileUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (file == null) {
            notFindParams.add("file");
        }
        throwParamNotFindException(request.getRequestURI());
        if (file.isEmpty()) {
            logger.info("文件为空空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的文件名："+fileName);
        //文件的contentType
        String fileType = file.getContentType();
        logger.info("文件的contentType:"+fileType);
        // 新文件名
        fileName = UUID.randomUUID() + suffixName;
        logger.info(" 新文件名：" + fileName);
        String realPath;
        if (StringUtils.isEmpty(imageFilePath)) {
            realPath = request.getSession().getServletContext().getRealPath(FILE_URL_PREFIX);
        } else {
            realPath = imageFilePath;
        }
        File dest = new File(realPath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("URL路径：" + FILE_URL_PREFIX + fileName);
        logger.info("本机路径：" + dest.getPath());
        UserDto loginUser = getLoginUser();
        loginUser.setHeadImg(FILE_URL_PREFIX + fileName);
        UserDto userDto = userService.updateById(loginUser);
        return new ResponseDto(0, "修改头像成功", userDto);

    }
}
