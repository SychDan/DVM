package ru.mephi.kaf82.DVM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mephi.kaf82.DVM.repository.EntryRepository;

import javax.annotation.Resource;

@Controller
public class HomeController {

    @Resource
    private EntryRepository entryRepository;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home(Model model) {
        entryRepository.findAll();
        model.addAttribute("name", "Danny");
        return "index";
    }
}
