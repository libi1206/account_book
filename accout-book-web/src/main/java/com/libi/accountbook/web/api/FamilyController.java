package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.FamilyDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.FamilyService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.libi.accountbook.web.constant.UrlConst.FAMILY_ROOT;
/**
 * @author libi
 */
@RestController
@RequestMapping(FAMILY_ROOT)
public class FamilyController extends BaseController implements BaseAttrController<FamilyDto> {
    @Reference
    private FamilyService familyService;

    @Override
    @CheckToken
    @PostMapping("/create")
    public ResponseDto create(FamilyDto familyDto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (familyDto.getFamilyName() == null) {
            notFindParams.add("familyName");
        }
        familyDto.setId(null);
        throwParamNotFindException(request.getRequestURI());
        AccFamily family = familyService.insert(familyDto, getLoginUser().getId());
        return new ResponseDto(0, "创建且加入成功", family);
    }

    @Override
    @CheckToken
    @PostMapping("/update")
    public ResponseDto update(FamilyDto familyDto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException {
        if (familyDto.getId() == null) {
            notFindParams.add("id");
        }
        throwParamNotFindException(request.getRequestURI());
        AccFamily family = familyService.update(familyDto,getLoginUser().getId());
        return new ResponseDto(0, "修改成功", family);
    }

    @Override
    @CheckToken
    @GetMapping("/getAll")
    public ResponseDto getAll(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseDto(0,"查询成功",familyService.selectAll(getLoginUser().getId()));
    }

    @Override
    @CheckToken
    @RequestMapping("/getAllPage")
    public ResponseDto getAllByPage(@RequestParam(defaultValue = "30")Integer rows,
                                    @RequestParam(defaultValue = "1") Integer page,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException {
        if (rows == null || rows <= 0) {
            notFindParams.add("rows");
        }
        if (page == null || page <= 0) {
            notFindParams.add("page");
        }
        throwParamNotFindException(request.getRequestURI());
        return new ResponseDto(0, "查询成功", familyService.selectByPage(rows, page, getLoginUser().getId()));
    }

    /**
     * 只是为了不报错，家庭不直接提供删除家庭的方法
     * @param id
     * @return
     * @throws AttrNotLoginUserException
     */
    @Override
    public ResponseDto deleteById(Long id,HttpServletRequest request, HttpServletResponse response) throws AttrNotLoginUserException {
        return null;
    }

    /**
     * 加入一个家庭
     * @param familyId
     * @return
     */
    @GetMapping("/join")
    @CheckToken
    public ResponseDto joinFamily(@RequestParam Long familyId) {
        if (familyId == null) {
            notFindParams.add("familyId");
        }
        return new ResponseDto(0, "加入成功", familyService.joinFamily(familyId, getLoginUser().getId()));
    }

    /**
     * 退出家庭
     * @param familyId
     * @return
     */
    @GetMapping("/quit")
    @CheckToken
    public ResponseDto quitFamily(@RequestParam Long familyId) {
        if (familyId == null) {
            notFindParams.add("familyId");
        }
        return new ResponseDto(0, "退出成功", familyService.quitFamily(familyId, getLoginUser().getId()));
    }

    @GetMapping("/getUser")
    @CheckToken
    public ResponseDto getAllUser(@RequestParam Long familyId) {
        if (familyId == null) {
             notFindParams.add("familyId");
        }
        return new ResponseDto(0, "查询成功", familyService.selectAllUserByFamily(familyId));
    }
}
