package com.coppolab.first_homework.entity;

import javax.persistence.*;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filename;

    @ManyToOne
    private User user;

}
