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
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.repository.PersonRepository;
import ru.mephi.kaf82.DVM.util.PersonValidator;

import javax.annotation.Resource;

@Controller
public class PersonalController {

    @Resource
    private PersonRepository personRepository;

    @Resource
    private PersonValidator personValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personValidator);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "persons";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("personForm") @Validated Person person, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "addOrEditPerson";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (person.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Пользователь успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Пользователь успешно обновлен");
            }
            personRepository.save(person);
        }
        model.addAttribute("persons", personRepository.findAll());
        return "redirect:/persons";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String addTerminal(Model model) {
        Person person = new Person();
        model.addAttribute("personForm", person);
        return "addOrEditPerson";
    }

    @RequestMapping(value = "/persons/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id).get();
        model.addAttribute("personForm", person);
        return "addOrEditPerson";
    }

    @RequestMapping(value = "/persons/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        personRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Пользователь успешно удален!");
        return "redirect:/persons";

    }
}
