package com.example.votingsystem.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name = "visitor")
@Data
public class Visitor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer vid;
    private String ip;
    private Timestamp time;
}
