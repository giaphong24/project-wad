package com.example.project.dto.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseTemplate<T> {
    private Integer code;
    private String message;
    @JsonInclude(Include.NON_NULL)
    private T data;

}
