package com.codegym.cms.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Min(0)
    private float area;
    @Min(0)
    private Long population;
    @Min(0)
    private float GDP;
    private String description;

    @ManyToOne
    private National national;

    public Province() {
    }

    public Province(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public float getGDP() {
        return GDP;
    }

    public void setGDP(float GDP) {
        this.GDP = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
