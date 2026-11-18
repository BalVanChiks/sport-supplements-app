package com.example.sportsupplements.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "proteins")
@DiscriminatorValue("Protein")
@Data
@EqualsAndHashCode(callSuper = true)
public class Protein extends SportSupplement {
    @Column(name = "protein_type")
    private String proteinType;

    @Column(name = "portion_size")
    private Double portionSize;

    @Override
    public String getType() { return "Протеин"; }
}