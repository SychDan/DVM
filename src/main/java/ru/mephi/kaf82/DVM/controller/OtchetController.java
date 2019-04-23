package ru.mephi.kaf82.DVM.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mephi.kaf82.DVM.model.Media;
import ru.mephi.kaf82.DVM.model.Otchet;
import ru.mephi.kaf82.DVM.model.Photo;
import ru.mephi.kaf82.DVM.repository.MediaRepository;
import ru.mephi.kaf82.DVM.repository.OtchetRepository;
import ru.mephi.kaf82.DVM.repository.PhotoRepository;
import ru.mephi.kaf82.DVM.util.OtchetValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Controller
public class OtchetController {

    @Resource
    private OtchetValidator otchetValidator;

    @Resource
    private OtchetRepository otchetRepository;

    @Resource
    private PhotoRepository photoRepository;

    @Resource
    private MediaRepository mediaRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(otchetValidator);
    }

    @RequestMapping(value = "/otchets", method = RequestMethod.GET)
    public String otchetList(Model model) {
        model.addAttribute("otchets", otchetRepository.findAll());
        return "otchets";
    }

    @RequestMapping(value = "/otchets", method = RequestMethod.POST)
    public String save(@ModelAttribute("otchetForm") @Validated Otchet otchet, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "addOrEditOtchet";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (otchet.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Отчет успешно добавлен");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Отчет успешно обновлен");
            }
            MultipartFile photoFile = otchet.getPhoto().getFile();
            MultipartFile mediaFile = otchet.getMedia().getFile();
            try {
                otchet.getPhoto().setContent(photoFile.getBytes());
                otchet.getPhoto().setContentType(photoFile.getContentType());
                otchet.getPhoto().setPhoto(photoFile.getOriginalFilename());
                otchet.getMedia().setContent(mediaFile.getBytes());
                otchet.getMedia().setContentType(mediaFile.getContentType());
                otchet.getMedia().setName(mediaFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
            otchet.setDate(Instant.now());
            mediaRepository.save(otchet.getMedia());
            photoRepository.save(otchet.getPhoto());
            otchet.setPhoto(otchet.getPhoto());
            otchetRepository.save(otchet);
        }
        model.addAttribute("otchets", otchetRepository.findAll());
        return "redirect:/otchets";
    }

    @RequestMapping(value = "/otchets/add", method = RequestMethod.GET)
    public String add(Model model) {
        Otchet otchet = new Otchet();
        Photo photo = new Photo();
        Media media = new Media();
        otchet.setDate(Instant.now());
        otchet.setPhoto(photo);
        otchet.setMedia(media);
        model.addAttribute("otchetForm", otchet);
        return "addOrEditOtchet";
    }

    @RequestMapping(value = "/otchets/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Otchet otchet = otchetRepository.findById(id).get();
        model.addAttribute("otchetForm", otchet);
        return "addOrEditOtchet";
    }

    @RequestMapping(value = "/otchets/{id}/delete", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        otchetRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Отчет успешно удален!");
        return "redirect:/otchets";
    }

    @RequestMapping(value = { "/otchets/{id}/downloadPhoto" }, method = RequestMethod.GET)
    public String downloadPhoto(@PathVariable long id, HttpServletResponse response) throws IOException {
        Otchet otchet = otchetRepository.findById(id).get();
        Photo photo = otchet.getPhoto();
        response.setContentType(photo.getContentType());
        response.setContentLength(photo.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + photo.getPhoto() +"\"");

        FileCopyUtils.copy(photo.getContent(), response.getOutputStream());

        return "redirect:/otchets";
    }

    @RequestMapping(value = { "/otchets/{id}/downloadMedia" }, method = RequestMethod.GET)
    public String downloadMedia(@PathVariable long id, HttpServletResponse response) throws IOException {
        Otchet otchet = otchetRepository.findById(id).get();
        Media media = otchet.getMedia();
        response.setContentType(media.getContentType());
        response.setContentLength(media.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + media.getName() +"\"");

        FileCopyUtils.copy(media.getContent(), response.getOutputStream());

        return "redirect:/otchets";
    }
}
