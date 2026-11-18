package com.example.sportsupplements.controller;

import com.example.sportsupplements.model.*;
import com.example.sportsupplements.service.SupplementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final SupplementService service;
    public OrderController(SupplementService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", service.getAllOrders());
        return "orders";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("supplements", service.getAllSupplements());
        return "order-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam Long supplementId, @RequestParam Integer quantity) {
        Order order = new Order();
        SportSupplement supplement = service.getSupplementById(supplementId);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setSupplement(supplement);
        item.setQuantity(quantity);
        order.getItems().add(item);

        service.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteOrder(id);
        return "redirect:/orders";
    }
}