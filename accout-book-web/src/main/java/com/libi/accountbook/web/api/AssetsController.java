package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.service.AssetsService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PostMapping("/create")
    public ResponseDto create(AssetsDto assetsDto,HttpServletRequest request) {
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
    @PostMapping("/update")
    public ResponseDto update(AssetsDto assetsDto,HttpServletRequest request) {
        if (assetsDto.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "修改成功",assetsService.update(assetsDto));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto getAll() {
        return new ResponseDto(0,"查询成功",assetsService.selectAll(getLoginUser().getId()));
    }

    @Override
    @RequestMapping("/getAllPage")
    public ResponseDto getAllByPage(@RequestParam Integer rows,@RequestParam Integer page) {
        if (rows == null || rows <= 0) {
            rows = 30;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        return new ResponseDto(0, "查询成功", assetsService.selectByPage(rows, page, getLoginUser().getId()));

    }
}
