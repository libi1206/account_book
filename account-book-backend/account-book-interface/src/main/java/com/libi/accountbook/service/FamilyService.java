package com.libi.accountbook.service;

import com.libi.accountbook.dto.FamilyDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.service.base.BaseAttrService;

import java.util.List;

/**
 * @author libi
 */
public interface FamilyService extends BaseAttrService<FamilyDto,AccFamily> {

    AccFamily joinFamily(Long familyId, Long useId);

    AccFamily quitFamily(Long familyId, Long id);

    List<UserDto> selectAllUserByFamily(Long familyId);
}
