package com.fazlyev.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3}")
    private String email;

    @PastOrPresent
    private Date birthday;
}
