package br.com.airport.main.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message;
    private Integer status;
    private T data;
    
}
