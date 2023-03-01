package com.fazlyev.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Birthday shouldn't be blank")
    @PastOrPresent
    private LocalDate birthday;
}
