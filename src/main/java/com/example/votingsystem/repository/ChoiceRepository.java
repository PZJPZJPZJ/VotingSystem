package com.example.votingsystem.repository;

import com.example.votingsystem.bean.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    public List<Choice> findAllByTid(Integer id);
}
