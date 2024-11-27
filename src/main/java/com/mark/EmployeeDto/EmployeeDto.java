package com.mark.EmployeeDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3,message = "Name should contains minimum 3 characters")
    private String name;

    @Email
    private String emailId;

    @Size(max = 10,min = 10,message = "Number should e 10 characters")
    private String mobile;


}

