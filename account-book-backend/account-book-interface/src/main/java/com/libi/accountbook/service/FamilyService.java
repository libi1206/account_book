package com.libi.accountbook.service;

import com.libi.accountbook.dto.FamilyDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.entity.AccUser;

import java.util.List;

/**
 * @author libi
 */
public interface FamilyService {

    AccFamily createNewFamilyAndAdd(FamilyDto familyDto, Long id);

    AccFamily updateFamily(FamilyDto familyDto);

    AccFamily selectFamilyById(Long id);

    List<AccFamily> selectAllFamilyInUser(Long userId);

    AccFamily joinFamily(Long familyId, Long useId);

    AccFamily quitFamily(Long familyId, Long id);

    List<UserDto> selectAllUserByFamily(Long familyId);
}
