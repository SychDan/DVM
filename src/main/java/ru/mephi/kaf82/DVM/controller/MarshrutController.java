package ru.mephi.kaf82.DVM.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mephi.kaf82.DVM.model.Marshrut;
import ru.mephi.kaf82.DVM.repository.MarshrutRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.MarshrutValidator;

import javax.annotation.Resource;

@Controller
public class MarshrutController {

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private MarshrutRepository marshrutRepository;

    @Resource
    private MarshrutValidator marshrutValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(marshrutValidator);
    }

    @RequestMapping(value = "/persons/marshruts/", method = RequestMethod.GET)
    public String marshruts(Model model) {
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("marshruts", marshrutRepository.findAll());
        return "marshruts";
    }

    @RequestMapping(value = "/persons/marshruts/", method = RequestMethod.POST)
    public String saveMarshrut(@ModelAttribute("marshrutForm") @Validated Marshrut marshrut, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("terminals", terminalRepository.findAll());
            return "addOrEditMarshrut";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (marshrut.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Маршрут успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Маршрут успешно обновлен");
            }
            model.addAttribute("terminals", terminalRepository.findAll());
            marshrutRepository.save(marshrut);
        }
        return "redirect:/persons/marshruts/";
    }

    @RequestMapping(value = "/persons/marshruts/add", method = RequestMethod.GET)
    public String add(Model model) {
        Marshrut marshrut = new Marshrut();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("marshrutForm", marshrut);
        return "addOrEditMarshrut";
    }


    @RequestMapping(value = "/persons/marshruts/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Marshrut camera = marshrutRepository.findById(id).get();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("marshrutForm", camera);
        return "addOrEditMarshrut";
    }

    @RequestMapping(value = "/persons/marshruts/{id}/delete", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        marshrutRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Маршрут успешно удален!");
        return "redirect:/persons/marshruts/";
    }
}
