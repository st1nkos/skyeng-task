package ru.zhdanov.postApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostItemDto {

    private String type;
    private int zip;
    private String address;
    private String name;
}
