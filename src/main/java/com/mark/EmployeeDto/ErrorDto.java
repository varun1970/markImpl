package com.mark.EmployeeDto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class ErrorDto {
    private String message;
    private String request;
    private Date date;

    public ErrorDto(String message, String request, Date date) {
        this.message = message;
        this.request = request;
        this.date = date;
    }
}
