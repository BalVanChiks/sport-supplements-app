package com.example.sportsupplements.service;

import com.example.sportsupplements.model.*;
import com.example.sportsupplements.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupplementService {

    private final SportSupplementRepository supplementRepo;
    private final ManufacturerRepository manufacturerRepo;
    private final OrderRepository orderRepo;

    public SupplementService(SportSupplementRepository supplementRepo,
                             ManufacturerRepository manufacturerRepo,
                             OrderRepository orderRepo) {
        this.supplementRepo = supplementRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.orderRepo = orderRepo;
    }

    public List<SportSupplement> getAllSupplements() {
        return StreamSupport.stream(supplementRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public SportSupplement getSupplementById(Long id) {
        return supplementRepo.findById(id).orElse(null);
    }

    public SportSupplement saveSupplement(SportSupplement supplement) {
        return supplementRepo.save(supplement);
    }

    public void deleteSupplement(Long id) {
        supplementRepo.deleteById(id);
    }

    public List<Manufacturer> getAllManufacturers() {
        return StreamSupport.stream(manufacturerRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerRepo.save(manufacturer);
    }

    public void deleteManufacturer(Long id) {
        manufacturerRepo.deleteById(id);
    }

    public List<Order> getAllOrders() {
        return StreamSupport.stream(orderRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}