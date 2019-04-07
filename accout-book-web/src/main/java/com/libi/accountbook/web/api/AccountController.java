package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.AccountService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 * 账本控制器
 */
@RestController
@RequestMapping(ACCOUNT_ROOT)
public class AccountController extends BaseController implements BaseAttrController<AccAccount> {
    @Reference
    private AccountService accountService;

    @PostMapping("/create")
    @Override
    public ResponseDto create(AccAccount accAccount,HttpServletRequest request) {
        if (accAccount.getAccountName() == null) {
            notFindParams.add("accountName");
        }
        throwParamNotFindException(request.getRequestURI());
        accAccount.setId(null);
        accAccount.setCreateTime(null);
        return new ResponseDto(0, "创建成功", accountService.insert(accAccount, getLoginUser().getId()));
    }

    @PostMapping("/update")
    @Override
    public ResponseDto update(AccAccount accAccount,HttpServletRequest request) {
        if (accAccount.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "修改成功", accountService.update(accAccount));
    }

    @GetMapping("/getAll")
    @Override
    public ResponseDto getAll() {
        return new ResponseDto(0, "查询成功", accountService.selectAll(getLoginUser().getId()));
    }
}
