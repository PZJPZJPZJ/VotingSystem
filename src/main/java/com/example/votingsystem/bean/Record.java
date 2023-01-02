package com.example.votingsystem.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "record")
@Data
public class Record {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer rid;
    private Integer cid;
    private Integer vid;
}
