package ru.zhdanov.postApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.zhdanov.postApp.enums.Status;


@Entity
@Table(name = "post_item")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;
    @NotNull
    private int zip;
    @NotNull
    private String address;
    @NotNull
    private String name;
    @Column(name = "status", columnDefinition = "int4")
    private Status status;


}
