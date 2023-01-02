package com.example.votingsystem.service;

import com.example.votingsystem.bean.Topic;

import java.util.List;

public interface TopicService {
    void insertTopic(Topic topic);  //添加标题
    void deleteTopic(Integer tid);  //删除用户
    List<Topic> selectTopic(); //查询所有标题
    List<Topic> selectTopicByTid(Integer id); //查询特定ID
    List<Topic> selectTopicByUsername(String un); //查询特定ID
}
