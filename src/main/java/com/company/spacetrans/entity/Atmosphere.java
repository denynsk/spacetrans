package com.company.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_ATMOSPHERE")
@Entity(name = "st_Atmosphere")
public class Atmosphere {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "PRESSURE")
    private Double pressure;

    @OneToMany(mappedBy = "atmosphere")
    private List<AtmosphericGas> gases;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "atmosphere")
    private Planet planet;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "atmosphere")
    private Moon moon;

    public void setGases(List<AtmosphericGas> gases) {
        this.gases = gases;
    }

    public List<AtmosphericGas> getGases() {
        return gases;
    }

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}