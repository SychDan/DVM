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
import ru.mephi.kaf82.DVM.model.Camera;
import ru.mephi.kaf82.DVM.repository.CameraRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.DeviceValidator;

import javax.annotation.Resource;

/**
 * Страница работы с устройствами
 */
@Controller
public class DeviceController {

    @Resource
    private CameraRepository cameraRepository;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private DeviceValidator deviceValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(deviceValidator);
    }

    @RequestMapping(value = "/persons/devices/", method = RequestMethod.GET)
    public String devices(Model model) {
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("devices", cameraRepository.findAll());
        return "devices";
    }

    @RequestMapping(value = "/persons/devices/", method = RequestMethod.POST)
    public String saveDevice(@ModelAttribute("cameraForm") @Validated Camera camera, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("terminals", terminalRepository.findAll());
            return "addOrEditDevice";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (camera.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Устройство успешно добавлено");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Устройство успешно обновлено");
            }
            model.addAttribute("terminals", terminalRepository.findAll());
            cameraRepository.save(camera);
        }
        return "redirect:/persons/devices/";
    }

    @RequestMapping(value = "/persons/devices/add", method = RequestMethod.GET)
    public String add(Model model) {
        Camera person = new Camera();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("cameraForm", person);
        return "addOrEditDevice";
    }


    @RequestMapping(value = "/persons/devices/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Camera camera = cameraRepository.findById(id).get();
        model.addAttribute("terminals", terminalRepository.findAll());
        model.addAttribute("cameraForm", camera);
        return "addOrEditDevice";
    }

    @RequestMapping(value = "/persons/devices/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        cameraRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Устройство успешно успешно удалено!");
        return "redirect:/persons/devices/";
    }
}
