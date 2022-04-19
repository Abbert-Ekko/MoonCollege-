package com.app.eduService.exception;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoonException extends RuntimeException {

    private Integer code;

    private String message;
}
