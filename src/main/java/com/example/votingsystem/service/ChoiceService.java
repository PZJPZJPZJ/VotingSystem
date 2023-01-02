package com.example.votingsystem.service;

import com.example.votingsystem.bean.Choice;

import java.util.List;

public interface ChoiceService {
    void insertChoice(Choice choice);  //添加选项
    void deleteChoice(Integer cid);  //删除选项
    List<Choice> selectChoice(); //查询所有选项
    List<Choice> selectChoiceByTid(Integer id); //查询特定ID
}
