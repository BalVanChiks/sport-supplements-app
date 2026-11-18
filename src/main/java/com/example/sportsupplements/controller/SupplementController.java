package com.example.sportsupplements.controller;

import com.example.sportsupplements.model.*;
import com.example.sportsupplements.service.SupplementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supplements")
public class SupplementController {

    private final SupplementService service;

    public SupplementController(SupplementService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("supplements", service.getAllSupplements());
        return "supplements";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("manufacturers", service.getAllManufacturers());
        return "supplement-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("supplement", service.getSupplementById(id));
        model.addAttribute("manufacturers", service.getAllManufacturers());
        return "supplement-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam String type,
                       @RequestParam String name,
                       @RequestParam Double price,
                       @RequestParam Double packageWeight,
                       @RequestParam Long manufacturerId,
                       @RequestParam String subtype,
                       @RequestParam Double portionSize) {

        Manufacturer manufacturer = service.getAllManufacturers().stream()
                .filter(m -> m.getId().equals(manufacturerId))
                .findFirst()
                .orElse(null);

        SportSupplement supplement;
        if ("Kreatin".equals(type)) {
            Kreatin k = new Kreatin();
            k.setKreatinType(subtype);
            k.setPortionSize(portionSize);
            supplement = k;
        } else {
            Protein p = new Protein();
            p.setProteinType(subtype);
            p.setPortionSize(portionSize);
            supplement = p;
        }

        supplement.setName(name);
        supplement.setPrice(price);
        supplement.setPackageWeight(packageWeight);
        supplement.setManufacturer(manufacturer);

        service.saveSupplement(supplement);
        return "redirect:/supplements";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteSupplement(id);
        return "redirect:/supplements";
    }

    @PostMapping("/discount/{id}")
    public String applyDiscount(@PathVariable Long id) {
        SportSupplement s = service.getSupplementById(id);
        if (s != null) {
            s.orderWithDiscount();
            service.saveSupplement(s);
        }
        return "redirect:/supplements";
    }
}