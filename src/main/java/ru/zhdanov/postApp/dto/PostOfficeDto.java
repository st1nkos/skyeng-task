package ru.zhdanov.postApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostOfficeDto {
    private String index;
    private String name;
    private String address;
}
