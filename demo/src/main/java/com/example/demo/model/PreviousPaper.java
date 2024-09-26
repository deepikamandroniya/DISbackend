package com.example.demo.model;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pyq")
public class PreviousPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semester;
    private String type;
    private String course;
    private int year;


    @Column(name = "file_path")
    private String filePath;
}
