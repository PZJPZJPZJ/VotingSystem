package com.example.votingsystem.controller;

import com.example.votingsystem.bean.Choice;
import com.example.votingsystem.bean.Record;
import com.example.votingsystem.bean.Topic;
import com.example.votingsystem.bean.Visitor;
import com.example.votingsystem.service.ChoiceService;
import com.example.votingsystem.service.RecordService;
import com.example.votingsystem.service.TopicService;
import com.example.votingsystem.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Controller
public class VisitorController {
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private RecordService recordService;
    @GetMapping("/visitor/index")
    public String toIndex(Model model) {
        Collection<Topic> topics = topicService.selectTopic();
        model.addAttribute("topics",topics);
        return "visitor/index";
    }

    @GetMapping("/visitor/detail")
    public String toDetail(Model model,Integer id) {
        Collection<Choice> choices = choiceService.selectChoiceByTid(id);
        Collection<Topic> topics = topicService.selectTopicByTid(id);
        model.addAttribute("choices", choices);
        model.addAttribute("topics",topics);
        return "visitor/detail";
    }
    @RequestMapping("/visitor/detail")
    public String record(Record record, Visitor visitor, HttpServletRequest httpServletRequest){
        visitor.setIp(visitorService.getIpAddr(httpServletRequest));//获取客户端IP
        visitor.setTime(new Timestamp(System.currentTimeMillis()));//获取当前时间
        visitorService.insertVisitor(visitor);     //获取到游客IP与当前时间插入到Visitor表
        record.setVid(visitor.getVid());           //获取Vid
        recordService.insertRecord(record);        //获取到游客ID与选项值插入到record表
        return "redirect:/visitor/index";
    }
    @RequestMapping("visitor/pie")
    @ResponseBody
    public List<Choice> findById(Integer id){
        List<Choice> choiceList = choiceService.selectChoiceByTid(id);
        return choiceList;
    }

    @GetMapping("visitor/showpie")
    public String toPie(Model model,Integer id){
        Collection<Topic> topics = topicService.selectTopicByTid(id);
        model.addAttribute("topics", topics);
        return "visitor/pie";
    }
}
