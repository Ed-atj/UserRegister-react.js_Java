package com.estudos.app.mapper;

import com.estudos.app.dto.UserDto;
import com.estudos.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mapping(target = "dataDeNascimento", source = "dataDeNascimento", qualifiedByName = "dateToString")
    UserDto toDto (User user);
    @Mapping(target = "dataDeNascimento", source = "dataDeNascimento", qualifiedByName = "stringToDate")
    User toUser(UserDto userDto);

    List<UserDto> listUserToDto(List<User> userList);

    @Named("stringToDate")
    default LocalDate stringToDate(String date){
        return LocalDate.parse(date, DATE_FORMAT);
    }
    @Named("dateToString")
    default String dateToString(LocalDate date){
        return date != null ? date.format(DATE_FORMAT): null;
    }

    default Optional<UserDto> optionalToDto(Optional<User> userOptional) {
        return userOptional.map(this::toDto);
    }

}
