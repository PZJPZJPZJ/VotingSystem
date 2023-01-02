package com.example.votingsystem.repository;

import com.example.votingsystem.bean.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
}
