package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.FamilyDto;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.service.FamilyService;
import com.libi.accountbook.web.api.base.BaseAttrController;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 */
@RestController
@RequestMapping(FAMILY_ROOT)
public class FamilyController extends BaseController implements BaseAttrController<FamilyDto> {
    @Reference
    private FamilyService familyService;

    @Override
    @PostMapping("/create")
    public ResponseDto create(FamilyDto familyDto) {
        AccFamily family = familyService.createNewFamilyAndAdd(familyDto, getLoginUser().getId());
        return new ResponseDto(0, "创建且加入成功", family);
    }

    @Override
    @PostMapping("/update")
    public ResponseDto update(FamilyDto familyDto) {
        AccFamily family = familyService.updateFamily(familyDto);
        return new ResponseDto(0, "修改成功", family);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto getAll() {
        return new ResponseDto(0,"查询成功",familyService.selectAllFamilyInUser(getLoginUser().getId()));
    }

    /**
     * 加入一个家庭
     * @param familyId
     * @return
     */
    @GetMapping("/join")
    public ResponseDto joinFamily(@RequestParam Long familyId) {
        return new ResponseDto(0, "加入成功", familyService.joinFamily(familyId, getLoginUser().getId()));
    }

    /**
     * 退出家庭
     * @param familyId
     * @return
     */
    @GetMapping("/quit")
    public ResponseDto quitFamily(@RequestParam Long familyId) {
        return new ResponseDto(0, "退出成功", familyService.quitFamily(familyId, getLoginUser().getId()));
    }

    @GetMapping("/getUser")
    public ResponseDto getAllUser(@RequestParam Long familyId) {
        return new ResponseDto(0, "查询成功", familyService.selectAllUserByFamily(familyId));
    }
}