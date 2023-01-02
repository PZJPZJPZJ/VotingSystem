package com.example.votingsystem.service;

import com.example.votingsystem.bean.Visitor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VisitorService {
    void insertVisitor(Visitor visitor);  //添加用户
    void deleteVisitor(Integer vid);  //删除用户
    List<Visitor> selectVisitor(); //查询用户
    String getIpAddr(HttpServletRequest request);
}
