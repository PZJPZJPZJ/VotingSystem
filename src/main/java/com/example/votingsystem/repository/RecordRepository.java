package com.example.votingsystem.repository;

import com.example.votingsystem.bean.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    public Integer countAllByCid(Integer id);
}
