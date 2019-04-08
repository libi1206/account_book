package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccTransactionRecord;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.NoMoneyException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.RecordService;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.libi.accountbook.web.constant.UrlConst.RECORD_ROOT;

/**
 * @author libi
 */
@RestController
@RequestMapping(RECORD_ROOT)
public class RecordController extends BaseController {
    @Reference
    private RecordService recordService;


    @PostMapping("/create")
    public ResponseDto create(AccTransactionRecord recordDto,HttpServletRequest request) throws ParamNotFindException, AttrNotLoginUserException, NoMoneyException {
        if (recordDto.getAccountId() == null) {
            notFindParams.add("accountId");
        }
        if (recordDto.getAmount() == null) {
            notFindParams.add("amount");
        }
        if (recordDto.getAccountId() == null) {
            notFindParams.add("assetsId");
        }
        if (recordDto.getTypeId() == null) {
            notFindParams.add("typeId");
        }
        if (recordDto.getCreateTime() == null) {
            notFindParams.add("createTime");
        }
        throwParamNotFindException(request.getRequestURI());
        recordDto.setId(null);
        return new ResponseDto(0, "添加记录成功", recordService.insertRecord(recordDto, getLoginUser().getId()));
    }

    /**
     * 这里只允许修改记录的备注和TypeId
     */
    @PostMapping("/update")
    public ResponseDto update(@RequestParam Long id, @RequestParam String note, @RequestParam Long typeId,HttpServletRequest request) throws ParamNotFindException, AttrNotLoginUserException {
        if (id == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "更新成功", recordService.updateById(id, note, typeId, getLoginUser().getId()));
    }

    @PostMapping("/query")
    public ResponseDto getAll(@RequestParam Integer rows,@RequestParam Integer page, RecordQueryConditionDto recordQueryConditionDto,HttpServletRequest request) throws ParamNotFindException {
        if (rows == null || rows <= 0) {
            rows = 30;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "查询成功", recordService.selectByCondition(page,rows,recordQueryConditionDto, getLoginUser().getId()));
    }
}
