package com.example.votingsystem.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "choice")
@Data
public class Choice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cid;
    private Integer tid;
    private String content;
    private String picture;
    private Integer total;
}
