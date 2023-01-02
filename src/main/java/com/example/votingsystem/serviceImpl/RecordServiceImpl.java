package com.example.votingsystem.serviceImpl;

import com.example.votingsystem.bean.Record;
import com.example.votingsystem.repository.RecordRepository;
import com.example.votingsystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void insertRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public List<Record> selectRecord() {
        return recordRepository.findAll();
    }

    @Override
    public Integer countRecordByCid(Integer id){
        return recordRepository.countAllByCid(id);
    }
}
