package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccTreasury;
import com.libi.accountbook.service.TreasuryService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.libi.accountbook.web.constant.UrlConst.TREASURY_ROOT;
/**
 * @author libi
 */
@RestController
@RequestMapping(TREASURY_ROOT)
public class TreasuryController extends BaseController implements BaseAttrController<AccTreasury> {
    @Reference
    private TreasuryService treasuryService;

    @Override
    public ResponseDto create(AccTreasury accTreasury) {
        return new ResponseDto(0,"创建成功",treasuryService.insert(accTreasury,getLoginUser().getId()));
    }

    @Override
    public ResponseDto update(AccTreasury accTreasury) {
        return new ResponseDto(0,"修改成功",treasuryService.update(accTreasury));
    }

    @Override
    public ResponseDto getAll() {
        return new ResponseDto(0,"查询成功",treasuryService.selectAll(getLoginUser().getId()));
    }
}
