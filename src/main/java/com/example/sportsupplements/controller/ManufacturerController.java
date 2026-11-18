package com.example.sportsupplements.controller;

import com.example.sportsupplements.model.Manufacturer;
import com.example.sportsupplements.service.SupplementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final SupplementService service;
    public ManufacturerController(SupplementService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("manufacturers", service.getAllManufacturers());
        return "manufacturers";
    }

    @GetMapping("/new")
    public String newForm() { return "manufacturer-form"; }

    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam(required = false) String country) {
        Manufacturer m = new Manufacturer();
        m.setName(name);
        m.setCountry(country);
        service.saveManufacturer(m);
        return "redirect:/manufacturers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteManufacturer(id);
        return "redirect:/manufacturers";
    }
}