package ru.zhdanov.postApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostMovementDto {

    private String movementType;
    private LocalDateTime movementDate;
}
