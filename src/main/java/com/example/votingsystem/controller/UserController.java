package com.example.votingsystem.controller;

import com.example.votingsystem.bean.Choice;
import com.example.votingsystem.bean.Topic;
import com.example.votingsystem.service.ChoiceService;
import com.example.votingsystem.service.RecordService;
import com.example.votingsystem.service.TopicService;
import com.example.votingsystem.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private RecordService recordService;
    @GetMapping("/user/add")
    public String toAdd(Model model) {
        Collection<Topic> topics = topicService.selectTopic();
        model.addAttribute("topics", topics);
        return "user/add";
    }

    @PostMapping("/user/add")
    public String add(Topic topic,String[] content,String[] picture) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        topic.setTime(new Timestamp(System.currentTimeMillis()));
        topic.setUsername(username);
        topicService.insertTopic(topic);
        int count = 0;
        for (String s : content) {
            Choice choice = new Choice();
            choice.setContent(s);
            choice.setPicture(picture[count]);
            choice.setTid(topic.getTid());
            choiceService.insertChoice(choice);
            count++;
        }
        return "redirect:/user/list";
    }

    @GetMapping("user/list")
    public String toList(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Collection<Topic> topics = topicService.selectTopicByUsername(username);
        model.addAttribute("topics", topics);
        return "user/list";
    }
    @RequestMapping("/user/list")
    public String list() {
        return "user/list";
    }

    @RequestMapping("user/update")//结算投票
    public String endVoting(Integer id){
        List<Topic> topicList = topicService.selectTopicByTid(id);
        List<Choice> choiceList = choiceService.selectChoiceByTid(id);//根据tid查对应choice
        for (Choice choice : choiceList) {
            Integer countRecord = recordService.countRecordByCid(choice.getCid());//根据每个cid查记录数量
            //调用choice表的修改方法，分别更新count的值
            choice.setTotal(countRecord);
            choiceService.insertChoice(choice);
        }
        //调topic表的修改方法，更新enable的值为0
        for (Topic topic : topicList) {
            topic.setEnable(false);
            topicService.insertTopic(topic);
        }
        return "redirect:/visitor/showpie?id="+id;
    }
    @RequestMapping("user/delete")//结算投票
    public String deleteVoting(Integer id){
        List<Choice> choiceList = choiceService.selectChoiceByTid(id);//根据tid查对应choice
        for (Choice choice : choiceList) {
            choiceService.deleteChoice(choice.getCid());
        }
        topicService.deleteTopic(id);
        return "redirect:/user/list";
    }
}
