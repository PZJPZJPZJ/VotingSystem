package com.example.votingsystem.repository;

import com.example.votingsystem.bean.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    public List<Topic> findAllByTid(Integer id);
    public List<Topic> findAllByUsername(String un);
}
