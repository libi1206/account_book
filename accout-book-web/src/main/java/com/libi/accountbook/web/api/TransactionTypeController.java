package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.TransactionTypeDto;
import com.libi.accountbook.entity.AccTransactionType;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.TransactionTypeService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 * 这里会写实体类和DTO类的转换的业务
 */
@RestController
@RequestMapping(TYPE_ROOT)
public class TransactionTypeController extends BaseController implements BaseAttrController<TransactionTypeDto> {
    @Reference
    private TransactionTypeService transactionTypeService;

    @Override
    @RequestMapping("/create")
    public ResponseDto create(TransactionTypeDto transactionTypeDto,HttpServletRequest request) throws ParamNotFindException {
        if (transactionTypeDto.getTypeName() == null) {
            notFindParams.add("typeName");
        }
        if (transactionTypeDto.getIncome() == null) {
            notFindParams.add("income");
        }
        throwParamNotFindException(request.getRequestURI());
        transactionTypeDto.setId(null);
        transactionTypeDto.setCreateTime(null);
        return new ResponseDto(0, "创建成功", transactionTypeService.insert(transactionTypeDto, getLoginUser().getId()));
    }

    @Override
    @RequestMapping("/update")
    public ResponseDto update(TransactionTypeDto transactionTypeDto,HttpServletRequest request) throws ParamNotFindException, AttrNotLoginUserException {
        if (transactionTypeDto.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "修改成功", transactionTypeService.update(transactionTypeDto,getLoginUser().getId()));
    }

    @Override
    @RequestMapping("/getAll")
    /**
     * 现在这个方法只能最多两层分类
     */
    public ResponseDto getAll() {
        List<AccTransactionType> resultList = transactionTypeService.selectAll(getLoginUser().getId());
        List<TransactionTypeDto> dtoList = new ArrayList<>();
        //装入所有的父节点,并且在结果集合去掉父节点
        for (AccTransactionType accTransactionType : resultList) {
            //判断是不是父节点
            if (accTransactionType.getParentId() == null) {
                //这里创建了父节点DTO但是并没有注入子节点信息
                dtoList.add(new TransactionTypeDto(accTransactionType));
            }
        }
        //遍历剩下的子节点，找到对应的父节点装入,直到结果集里没有结果了说明分配完毕
        for (AccTransactionType childType : resultList) {
            Long parentId = childType.getParentId();
            for (TransactionTypeDto prentDto : dtoList) {
                if (prentDto.getId().equals(parentId)) {
                    prentDto.setChildType(new TransactionTypeDto(childType));
                    break;
                }
            }
        }
        return new ResponseDto(0, "查询成功", dtoList);
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
        return new ResponseDto(0, "查询成功", transactionTypeService.selectByPage(rows, page, getLoginUser().getId()));
    }
}
