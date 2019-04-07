package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.RecordDto;
import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccTransactionRecord;
import com.libi.accountbook.service.RecordService;
import com.libi.accountbook.service.base.BaseAttrService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 */
@RestController
@RequestMapping(RECORD_ROOT)
public class RecordController extends BaseController {
    @Reference
    private RecordService recordService;


    @PostMapping("/create")
    public ResponseDto create(AccTransactionRecord recordDto,HttpServletRequest request) {
        if (recordDto.getAccountId() == null) {
            notFindParams.add("accountId");
        }
        if (recordDto.getAmount() == null) {
            notFindParams.add("amount");
        }
        if (recordDto.getAccountId() == null) {
            notFindParams.add("assets");
        }
        if (recordDto.getTypeId() == null) {
            notFindParams.add("typeId");
        }
        throwParamNotFindException(request.getRequestURI());
        recordDto.setId(null);
        recordDto.setCreateTime(null);
        return new ResponseDto(0, "添加记录成功", recordService.insertRecord(recordDto, getLoginUser().getId()));
    }

    /**
     * 这里只允许修改记录的备注和TypeId
     */
    @PostMapping("/update")
    public ResponseDto update(@RequestParam Long id, @RequestParam String note, @RequestParam Long typeId,HttpServletRequest request) {
        if (id == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "更新成功", recordService.updateById(id, note, typeId));
    }

    @PostMapping("/query")
    public ResponseDto getAll(RecordQueryConditionDto recordQueryConditionDto) {
        return new ResponseDto(0, "查询成功", recordService.selectByCondition(recordQueryConditionDto, getLoginUser().getId()));
    }
}
