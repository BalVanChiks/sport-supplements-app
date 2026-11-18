package com.example.sportsupplements.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "supplements")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "supp_type")
@Data
public abstract class SportSupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "package_weight", nullable = false)
    private Double packageWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public double calculatePricePerGram() {
        return packageWeight > 0 ? price / packageWeight : 0;
    }

    public double calculatePortions(double portionWeight) {
        return packageWeight > 0 ? packageWeight / portionWeight : 0;
    }

    public void orderWithDiscount() {
        this.price = Math.max(0, this.price - 1000);
    }

    public abstract String getType();
}