package com.example.votingsystem.serviceImpl;

import com.example.votingsystem.bean.Topic;
import com.example.votingsystem.repository.TopicRepository;
import com.example.votingsystem.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void insertTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Integer tid) {
        topicRepository.deleteById(tid);
    }

    @Override
    public List<Topic> selectTopic() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> selectTopicByTid(Integer id){
        return topicRepository.findAllByTid(id);
    }

    @Override
    public List<Topic> selectTopicByUsername(String un){
        return topicRepository.findAllByUsername(un);
    }
}
