package com.example.votingsystem.serviceImpl;

import com.example.votingsystem.bean.Choice;
import com.example.votingsystem.repository.ChoiceRepository;
import com.example.votingsystem.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChoiceServiceImpl implements ChoiceService {
    @Autowired
    private ChoiceRepository choiceRepository;
    @Override
    public void insertChoice(Choice choice) {
        choiceRepository.save(choice);
    }

    @Override
    public void deleteChoice(Integer cid) {
        choiceRepository.deleteById(cid);
    }

    @Override
    public List<Choice> selectChoice() {
        return choiceRepository.findAll();
    }

    @Override
    public List<Choice> selectChoiceByTid(Integer id){
        return choiceRepository.findAllByTid(id);
    }
}
