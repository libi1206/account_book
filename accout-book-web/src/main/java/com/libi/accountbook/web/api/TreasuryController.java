package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccTreasury;
import com.libi.accountbook.service.TreasuryService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @PostMapping("/create")
    public ResponseDto create(AccTreasury accTreasury, HttpServletRequest request) {
        if (accTreasury.getTreasuryName() == null) {
            notFindParams.add("treasuryName");
        }
        if (accTreasury.getMoner() == null) {
            notFindParams.add("moner");
        }
        throwParamNotFindException(request.getRequestURI());
        accTreasury.setId(null);
        return new ResponseDto(0,"创建成功",treasuryService.insert(accTreasury,getLoginUser().getId()));
    }

    @Override
    @PostMapping("/update")
    public ResponseDto update(AccTreasury accTreasury, HttpServletRequest request) {
        if (accTreasury.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0,"修改成功",treasuryService.update(accTreasury));
    }

    @Override
    @GetMapping("getAll")
    public ResponseDto getAll() {
        return new ResponseDto(0,"查询成功",treasuryService.selectAll(getLoginUser().getId()));
    }
}
