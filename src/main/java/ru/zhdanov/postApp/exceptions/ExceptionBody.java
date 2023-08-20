package ru.zhdanov.postApp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionBody {
    private String message;
    private Map<String,String> errors;

    public ExceptionBody(String msg){
        this.message=msg;
    }
}
