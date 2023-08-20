package ru.zhdanov.postApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zhdanov.postApp.enums.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_movement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movementType;
    private LocalDateTime movementDate;
    @Column(name = "status", columnDefinition = "int4")
    private Status movementStatus;

    @ManyToOne
    @JoinColumn(name = "post_item_id",columnDefinition = "int4")
    private PostItem postItem;

    @ManyToOne
    @JoinColumn(name = "post_office_id" ,columnDefinition = "int4")
    private PostOffice postOffice;
}
