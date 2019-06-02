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
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Otchet;
import ru.mephi.kaf82.DVM.model.Type;
import ru.mephi.kaf82.DVM.repository.FileRepository;
import ru.mephi.kaf82.DVM.repository.OtchetRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.service.AuthService;
import ru.mephi.kaf82.DVM.util.FileUtil;
import ru.mephi.kaf82.DVM.util.HashCalculator;
import ru.mephi.kaf82.DVM.util.OtchetValidator;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;

/**
 * Страницы работ с отчетами
 */
@Controller
public class OtchetController {

    @Resource
    private OtchetValidator otchetValidator;

    @Resource
    private OtchetRepository otchetRepository;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private AuthService authService;

    @Resource
    private FileRepository fileRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(otchetValidator);
    }

    @RequestMapping(value = "/otchets", method = RequestMethod.GET)
    public String otchetList(Model model) {
        model.addAttribute("otchets", otchetRepository.findAll());
        model.addAttribute("terminals", terminalRepository.findAll());
        return "otchets";
    }

    @RequestMapping(value = "/otchets", method = RequestMethod.POST)
    public String save(@ModelAttribute("otchetForm") @Validated Otchet otchet, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("terminals", terminalRepository.findAll());
            return "addOrEditOtchet";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (otchet.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Отчет успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Отчет успешно обновлен");
            }
            for (File photo : otchet.getPhoto()) {
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (File media : otchet.getMedia()) {
                try {
                    String filename = media.getFile().getOriginalFilename();
                    String hash = HashCalculator.getSHA256String(media.getFile().getBytes());
                    byte[] fileData = media.getFile().getBytes();
                    if (fileRepository.countByHash(hash) == 0) {
                        FileUtil.saveFile(hash, fileData);
                    }
                    media.setName(filename);
                    media.setHash(hash);
                    media.setType(Type.MEDIA);
                    media.setDate(Instant.now());
                    fileRepository.save(media);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            otchet.setPerson(authService.getLoginedUser());
            otchet.setDate(Instant.now());
            otchetRepository.save(otchet);
        }
        model.addAttribute("otchets", otchetRepository.findAll());
        return "redirect:/otchets";
    }

    @RequestMapping(value = "/otchets/add", method = RequestMethod.GET)
    public String add(Model model) {
        Otchet otchet = new Otchet();
        otchet.setDate(Instant.now());
        model.addAttribute("otchetForm", otchet);
        model.addAttribute("terminals", terminalRepository.findAll());
        return "addOrEditOtchet";
    }

    @RequestMapping(value = "/otchets/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Otchet otchet = otchetRepository.findById(id).get();
        model.addAttribute("otchetForm", otchet);
        model.addAttribute("terminals", terminalRepository.findAll());
        return "addOrEditOtchet";
    }

    @RequestMapping(value = "/otchets/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        otchetRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Отчет успешно удален!");
        return "redirect:/otchets";
    }
}
