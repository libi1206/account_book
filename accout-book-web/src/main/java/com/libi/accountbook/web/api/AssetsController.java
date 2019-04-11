package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.AssetsService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 */
@RestController
@RequestMapping(ASSETS_ROOT)
public class AssetsController extends BaseController implements BaseAttrController<AssetsDto> {
    @Reference
    private AssetsService assetsService;

    @Override
    @CheckToken
    @PostMapping("/create")
    public ResponseDto create(AssetsDto assetsDto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (assetsDto.getAssetsName() == null) {
            notFindParams.add("assetsName");
        }
        if (assetsDto.getMoner() == null) {
            notFindParams.add("moner");
        }
        if (assetsDto.getOneWay() == null) {
            notFindParams.add("oneWay");
        }
        throwParamNotFindException(request.getRequestURI());
        assetsDto.setId(null);
        AccAssets assets = assetsService.insert(assetsDto,getLoginUser().getId());
        return new ResponseDto(0, "创建成功", assets);
    }

    @Override
    @CheckToken
    @PostMapping("/update")
    public ResponseDto update(AssetsDto assetsDto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException {
        if (assetsDto.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "修改成功",assetsService.update(assetsDto,getLoginUser().getId()));
    }

    @Override
    @CheckToken
    @GetMapping("/getAll")
    public ResponseDto getAll(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseDto(0,"查询成功",assetsService.selectAll(getLoginUser().getId()));
    }

    @Override
    @CheckToken
    @RequestMapping("/getAllPage")
    public ResponseDto getAllByPage(@RequestParam(defaultValue = "30")Integer rows,
                                    @RequestParam(defaultValue = "1") Integer page,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (rows == null || rows <= 0) {
            notFindParams.add("rows");
        }
        if (page == null || page <= 0) {
            notFindParams.add("page");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "查询成功", assetsService.selectByPage(rows, page, getLoginUser().getId()));

    }

    @GetMapping("/delete")
    @CheckToken
    @Override
    public ResponseDto deleteById(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response) throws AttrNotLoginUserException {
        return new ResponseDto(0, "删除成功", assetsService.deleteById(id, getLoginUser().getId()));
    }
}
