package com.example.votingsystem.serviceImpl;

import com.example.votingsystem.bean.Visitor;
import com.example.votingsystem.repository.VisitorRepository;
import com.example.votingsystem.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public void insertVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    @Override
    public void deleteVisitor(Integer vid) {
        visitorRepository.deleteById(vid);
    }

    @Override
    public List<Visitor> selectVisitor() {
        return visitorRepository.findAll();
    }

    @Override
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
