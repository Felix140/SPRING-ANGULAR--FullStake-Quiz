package com.quiz.fullstakequiz.mappers;

import com.quiz.fullstakequiz.dto.AdminDto;
import com.quiz.fullstakequiz.dto.SignUpDto;
import com.quiz.fullstakequiz.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDto toAdminDto(Admin admin);

    @Mapping(target = "password", ignore = true)
    Admin signUpToAdmin(SignUpDto signUpDto);
}
