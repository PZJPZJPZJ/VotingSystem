package com.example.votingsystem.service;

import com.example.votingsystem.bean.Record;
import com.example.votingsystem.bean.Topic;

import java.util.List;

public interface RecordService {
    void insertRecord(Record record);  //添加记录
    List<Record> selectRecord(); //查询所有记录
    Integer countRecordByCid(Integer id);//根据id统计数量
}
