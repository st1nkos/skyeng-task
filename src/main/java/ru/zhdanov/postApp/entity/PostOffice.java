package ru.zhdanov.postApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Entity
@Table(name = "post_office")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String index;
    private String name;
    private String address;


}
