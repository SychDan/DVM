package ru.mephi.kaf82.DVM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Настройки
 */
@Controller
public class SettingController {

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings(Model model) {
        return "settings";
    }
}
