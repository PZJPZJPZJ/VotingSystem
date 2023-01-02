package com.example.votingsystem.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tid;
    private String username;
    private String title;
    private Timestamp time;
    private Boolean enable;
}
