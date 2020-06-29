package com.codegym.cms.controller;

import com.codegym.cms.model.National;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.NationalService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ProvinceController {

    @Autowired
    private NationalService nationalService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("nationals")
    public Iterable<National> nationals() {
        return nationalService.findAll();
    }

//    @GetMapping("/view-province/{id}")
//    public ModelAndView viewProvince(@PathVariable("id") Long id) {
//        Province province = provinceService.findById(id);
//        if (province == null) {
//            return new ModelAndView("/error.404");
//        }
//
//
//        ModelAndView modelAndView = new ModelAndView("/province/view");
//        modelAndView.addObject("province", province);
//        return modelAndView;
//    }

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }


    @PostMapping("/create-province")
    public String createProvince(@Valid @ModelAttribute("province") Province province, BindingResult bindingResult, Model model) {

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("message", "not empty name");
            return "province/create";
        } else {
            if (provinceService.findByName(province.getName()) != null) {
                model.addAttribute("message", "province existed");
                return "province/create";
            } else {

                provinceService.save(province);
                return "redirect:provinces";
            }
        }
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province", province);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {

        ModelAndView modelAndView = new ModelAndView("/province/edit");
        province.setId(province.getId());
        provinceService.save(province);
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "saved");


        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView;
        if (province != null) {
            modelAndView = new ModelAndView("/province/delete");
            modelAndView.addObject("province", province);

        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province) {
        provinceService.remove(province.getId());
        return "redirect:provinces";
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView;
        if (province != null) {
            modelAndView = new ModelAndView("/province/view");
            modelAndView.addObject("province", province);

        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }
}