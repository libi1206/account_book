package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.service.AssetsService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDto create(AssetsDto assetsDto) {
        AccAssets assets = assetsService.insertAssets(assetsDto,getLoginUser().getId());
        return new ResponseDto(0, "创建成功", assets);
    }

    @Override
    @PostMapping("/update")
    public ResponseDto update(AssetsDto assetsDto) {
        AccAssets assets = assetsService.updateAssets(assetsDto);
        return new ResponseDto(0, "修改成功", assets);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto getAll() {
        return new ResponseDto(0,"查询成功",assetsService.getAll(getLoginUser().getId()));
    }

}
