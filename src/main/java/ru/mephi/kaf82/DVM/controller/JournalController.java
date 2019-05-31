package ru.mephi.kaf82.DVM.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Otchet;
import ru.mephi.kaf82.DVM.model.Type;
import ru.mephi.kaf82.DVM.repository.EntryRepository;
import ru.mephi.kaf82.DVM.repository.FileRepository;
import ru.mephi.kaf82.DVM.repository.OtchetRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
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
    private FileRepository fIleRepository;

    @Resource
    private OtchetRepository otchetRepository;

    @RequestMapping(value = "/journals", method = RequestMethod.GET)
    public String EntityList(Model model) {
        model.addAttribute("journals", entryRepository.findAll());
        model.addAttribute("terminals", terminalRepository.findAll());
        return "journals";
    }

    @RequestMapping(value = "/journals/{id}/video", produces = "application/media")
    public String playVideo(@PathVariable("id") long id, HttpServletResponse response) {
        File media = entryRepository.findById(13L).get().getMedia();
        InputStream in = new ByteArrayInputStream(FileUtil.getFileBytes(media.getHash()));
        response.setHeader("Content-Disposition", "attachment; filename=" + media.getName());
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
        model.addAttribute("logs", fIleRepository.findByEntryAndType(entry, Type.LOG));
        model.addAttribute("photos", fIleRepository.findByEntryAndType(entry, Type.IMAGE));
        model.addAttribute("videos", fIleRepository.findByEntryAndType(entry, Type.MEDIA));
        return "showData";
    }

    @RequestMapping(value = "/journals/{id}/showData/{v_id}/video", produces = "application/media")
    public String showVideo(@PathVariable("id") long id, @PathVariable("v_id") long v_id, HttpServletResponse response) {
        File media = fIleRepository.findById(v_id).get();
        InputStream in = new ByteArrayInputStream(FileUtil.getFileBytes(media.getHash()));
        response.setHeader("Content-Disposition", "attachment; filename=" + media.getName());
        try {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "" + response;
    }

    @RequestMapping(value = "/journals/{id}/showData/{p_id}/photo")
    public String showPhoto(@PathVariable("id") long id, @PathVariable("p_id") long p_id, HttpServletResponse response) {
        File photo = fIleRepository.findById(p_id).get();
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

    @RequestMapping(value = "/journals/{id}/showData/{l_id}/log", method = RequestMethod.POST)
    @ResponseBody
    public String showLog(@PathVariable("id") long id, @PathVariable("l_id") long l_id) {
        File log = fIleRepository.findById(l_id).get();
        java.io.File file = new java.io.File(FileUtil.getFullClasspathFilename(log.getHash()));
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) sb.append(st);
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return sb.toString();
    }


    @RequestMapping(value = "/journals/{id}/showData/createOtchet", method = RequestMethod.POST)
    public String createOtchet(@PathVariable("id") long id) {
        Entry entry = entryRepository.findById(id).get();
        Otchet otchet = new Otchet();
        otchet.setPerson(entry.getPerson());
        otchet.setDate(Instant.now());
//        otchet.setMedia(entry.getMediaList());
//        otchet.setName("Othcet");
//        otchet.setPhoto(entry.getPhotos());
        otchetRepository.save(otchet);
        return "redirect:/otchets";
    }
}
