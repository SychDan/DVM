package ru.mephi.kaf82.DVM.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.model.Type;
import ru.mephi.kaf82.DVM.repository.FileRepository;
import ru.mephi.kaf82.DVM.repository.PersonRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.FileUtil;
import ru.mephi.kaf82.DVM.util.HashCalculator;
import ru.mephi.kaf82.DVM.util.PersonValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

@Controller
public class PersonalController {

    @Resource
    private PersonRepository personRepository;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private PersonValidator personValidator;

    @Resource
    private FileRepository fileRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personValidator);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("terminals", terminalRepository.findAll());
        return "persons";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("personForm") @Validated Person person, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("terminals", terminalRepository.findAll());
            return "addOrEditPerson";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (person.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Пользователь успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Пользователь успешно обновлен");
            }
            try {
                File photo = person.getPhoto();
                String filename = photo.getFile().getOriginalFilename();
                String hash = HashCalculator.getSHA256String(photo.getFile().getBytes());
                byte[] fileData = photo.getFile().getBytes();
                if (fileRepository.countByHash(hash) == 0) {
                    FileUtil.saveFile(hash, fileData);
                }
                photo.setName(filename);
                photo.setHash(hash);
                photo.setType(Type.IMAGE);
                photo.setDate(Instant.now());
                fileRepository.save(photo);
                person.setPhoto(photo);
                personRepository.save(person);
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
        model.addAttribute("persons", personRepository.findAll());
        return "redirect:/persons";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String add(Model model) {
        Person person = new Person();
        File photo = new File();
        person.setPhoto(photo);
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("personForm", person);
        return "addOrEditPerson";
    }


    @RequestMapping(value = "/persons/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id).get();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("personForm", person);
        return "addOrEditPerson";
    }

    @RequestMapping(value = "/persons/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        FileUtil.deleteFile(personRepository.findById(id).get().getPhoto().getHash());
        personRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Пользователь успешно удален!");
        return "redirect:/persons";
    }

    @RequestMapping(value = { "/persons/{id}/download" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable long id, HttpServletResponse response) throws IOException {
        Person person = personRepository.findById(id).get();
        File photo = person.getPhoto();
        response.setHeader("Content-Disposition","attachment; filename=\"" + photo.getName() +"\"");
        FileCopyUtils.copy(FileUtil.getFileBytes(photo.getHash()), response.getOutputStream());

        return "redirect:/persons";
    }

    @RequestMapping(value = "/persons/{id}/showData")
    public String showPhoto(@PathVariable("id") long id, HttpServletResponse response) {
        Person person = personRepository.findById(id).get();
        File photo = person.getPhoto();
        InputStream in = new ByteArrayInputStream(FileUtil.getFileBytes(photo.getHash()));
        response.setHeader("Content-Disposition", "attachment; filename=" + photo.getName());
        try {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "" + response;
    }
}
