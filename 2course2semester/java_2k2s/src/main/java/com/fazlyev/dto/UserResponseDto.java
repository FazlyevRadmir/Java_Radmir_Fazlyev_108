package com.fazlyev.dto;

import com.fazlyev.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private Date birthday;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .build();
    }
}
