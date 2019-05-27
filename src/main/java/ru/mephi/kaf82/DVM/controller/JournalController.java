package ru.mephi.kaf82.DVM.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.Media;
import ru.mephi.kaf82.DVM.model.Otchet;
import ru.mephi.kaf82.DVM.model.Photo;
import ru.mephi.kaf82.DVM.repository.EntryRepository;
import ru.mephi.kaf82.DVM.repository.FIleRepository;
import ru.mephi.kaf82.DVM.repository.MediaRepository;
import ru.mephi.kaf82.DVM.repository.OtchetRepository;
import ru.mephi.kaf82.DVM.repository.PhotoRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

@Controller
public class JournalController {

    @Resource
    private EntryRepository entryRepository;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private MediaRepository mediaRepository;

    @Resource
    private FIleRepository fIleRepository;

    @Resource
    private OtchetRepository otchetRepository;

    @Resource
    private PhotoRepository photoRepository;

    @RequestMapping(value = "/journals", method = RequestMethod.GET)
    public String EntityList(Model model) {
        model.addAttribute("journals", entryRepository.findAll());
        model.addAttribute("terminals", terminalRepository.findAll());
        return "journals";
    }

    @RequestMapping(value = "/journals/{id}/video", produces = "application/media")
    public String playVideo(@PathVariable("id") long id, HttpServletResponse response) {
        Media media = entryRepository.findById(id).get().getMedia();
        InputStream in = new ByteArrayInputStream(media.getContent());
        response.setHeader("Content-Disposition", "attachment; filename=" + media.getName());
        response.setHeader("Content-Length", String.valueOf(media.getSizeFound()));
        try {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ""+response;
    }

    @RequestMapping(value = "/journals/{id}/showData", method = RequestMethod.GET)
    public String showData(@PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).get();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("person", entry.getPerson());
        model.addAttribute("entry", entry);
//        model.addAttribute("logs", fIleRepository.findByEntry(entry));
//        model.addAttribute("photos", photoRepository.findByEntry(entry));
//        model.addAttribute("videos", mediaRepository.findByEntry(entry));
        return "showData";
    }

    @RequestMapping(value = "/journals/{id}/showData/{v_id}/video", produces = "application/media")
    public String showVideo(@PathVariable("id") long id, @PathVariable("v_id") long v_id, HttpServletResponse response) {
        Media media = mediaRepository.findById(v_id).get();
        InputStream in = new ByteArrayInputStream(media.getContent());
        response.setHeader("Content-Disposition", "attachment; filename=" + media.getName());
        response.setHeader("Content-Length", String.valueOf(media.getSizeFound()));
        try {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "" + response;
    }

    @RequestMapping(value = "/journals/{id}/showData/{p_id}/photo")
    public String showLog(@PathVariable("id") long id, @PathVariable("p_id") long v_id, HttpServletResponse response) {
        Photo photo = photoRepository.findById(v_id).get();
        InputStream in = new ByteArrayInputStream(photo.getContent());
        response.setContentType("image/jpeg");
        response.setHeader("Content-Length", String.valueOf(photo.getContent().length));
        try {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "" + response;
    }

    @RequestMapping(value = "/journals/{id}/showData/createOtchet", method = RequestMethod.POST)
    public String createOtchet(@PathVariable("id") long id) {
        Entry entry = entryRepository.findById(id).get();
        Otchet otchet = new Otchet();
        otchet.setPerson(entry.getPerson());
        otchet.setDate(Instant.now());
        otchet.setMedia(entry.getMediaList());
        otchet.setName("Othcet");
        otchet.setPhoto(entry.getPhotos());
        otchetRepository.save(otchet);
        return "redirect:/otchets";
    }
}
