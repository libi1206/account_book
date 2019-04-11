package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccTransactionRecord;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.NoMoneyException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.RecordService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.libi.accountbook.web.constant.UrlConst.RECORD_ROOT;

/**
 * @author libi
 */
@RestController
@RequestMapping(RECORD_ROOT)
public class RecordController extends BaseController {
    @Reference
    private RecordService recordService;


    @CheckToken
    @PostMapping("/create")
    public ResponseDto create(AccTransactionRecord recordDto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException, NoMoneyException {
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
        throwParamNotFindException(request.getRequestURI());
        recordDto.setId(null);
        return new ResponseDto(0, "添加记录成功", recordService.insertRecord(recordDto, getLoginUser().getId()));
    }

    /**
     * 这里只允许修改记录的备注和TypeId
     */
    @CheckToken
    @PostMapping("/update")
    public ResponseDto update(@RequestParam Long id, @RequestParam String note, @RequestParam Long typeId,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException {
        if (id == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "更新成功", recordService.updateById(id, note, typeId, getLoginUser().getId()));
    }

    @CheckToken
    @PostMapping("/query")
    public ResponseDto getAll(@RequestParam(defaultValue = "30")Integer rows,
                              @RequestParam(defaultValue = "1") Integer page,
                              RecordQueryConditionDto recordQueryConditionDto,
                              HttpServletRequest request,
                              HttpServletResponse response) throws ParamNotFindException {
        if (rows == null || rows <= 0) {
            notFindParams.add("rows");
        }
        if (page == null || page <= 0) {
            notFindParams.add("pages");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "查询成功", recordService.selectByCondition(page,rows,recordQueryConditionDto, getLoginUser().getId()));
    }
}
