package ru.mephi.kaf82.DVM.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mephi.kaf82.DVM.model.Terminal;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.TeminalValidator;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TerminalController {

    @Resource
    private MessageSource messageSource;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private TeminalValidator teminalValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(teminalValidator);
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView panel() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Terminal> terminals = terminalRepository.findAll();
        modelAndView.setViewName("home");
        modelAndView.addObject("terminals", terminals);
        return modelAndView;
    }

    @RequestMapping(value = "/home" , method = RequestMethod.POST)
    public String save(@ModelAttribute("terminalForm") @Validated Terminal terminal, BindingResult result, RedirectAttributes redirectAttributes , Model model) {
        List<String> groups = terminalRepository.findAllGroup();
        if (result.hasErrors()) {
            return "addOrEditTerminal";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (terminal.isNew()) {
                redirectAttributes.addFlashAttribute("msg" ,"Терминал успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg","Терминал успешно обновлен");
            }
            terminalRepository.save(terminal);
        }
        model.addAttribute("groups", groups);
        return "redirect:/home";
    }

    @RequestMapping(value = "/home/add", method = RequestMethod.GET)
    public String addTerminal(Model model) {
        Terminal terminal = new Terminal();
        model.addAttribute("terminalForm", terminal);
        return "addOrEditTerminal";
    }

    @RequestMapping(value = "/home/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Terminal terminal = terminalRepository.findById(id).get();
        model.addAttribute("terminalForm", terminal);
        return "addOrEditTerminal";
    }

    @RequestMapping(value = "/home/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        terminalRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Терминал успешно удален!");
        return "redirect:/home";

    }
}
