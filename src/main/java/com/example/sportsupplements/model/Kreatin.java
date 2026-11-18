package com.example.sportsupplements.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "kreatins")
@DiscriminatorValue("Kreatin")
@Data
@EqualsAndHashCode(callSuper = true)
public class Kreatin extends SportSupplement {
    @Column(name = "kreatin_type")
    private String kreatinType;

    @Column(name = "portion_size")
    private Double portionSize;

    @Override
    public String getType() { return "Креатин"; }
}