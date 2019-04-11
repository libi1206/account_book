package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.AccountService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @CheckToken
    @Override
    public ResponseDto create(AccAccount accAccount,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (accAccount.getAccountName() == null) {
            notFindParams.add("accountName");
        }
        throwParamNotFindException(request.getRequestURI());
        accAccount.setId(null);
        accAccount.setCreateTime(null);
        return new ResponseDto(0, "创建成功", accountService.insert(accAccount, getLoginUser().getId()));
    }

    @PostMapping("/update")
    @CheckToken
    @Override
    public ResponseDto update(AccAccount accAccount,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException {
        if (accAccount.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "修改成功", accountService.update(accAccount,getLoginUser().getId()));
    }

    @GetMapping("/getAll")
    @CheckToken
    @Override
    public ResponseDto getAll(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseDto(0, "查询成功", accountService.selectAll(getLoginUser().getId()));
    }

    @Override
    @CheckToken
    @RequestMapping("/getAllPage")
    public ResponseDto getAllByPage(@RequestParam(defaultValue = "30") Integer rows,
                                    @RequestParam(defaultValue = "1") Integer page,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (rows == null || rows <= 0) {
            notFindParams.add("rows");
        }
        if (page == null || page <= 0) {
            notFindParams.add("page");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "查询成功", accountService.selectByPage(rows, page, getLoginUser().getId()));
    }

    @GetMapping("/delete")
    @CheckToken
    @Override
    public ResponseDto deleteById(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response) throws AttrNotLoginUserException {
        return new ResponseDto(0, "删除成功", accountService.deleteById(id, getLoginUser().getId()));
    }
}
