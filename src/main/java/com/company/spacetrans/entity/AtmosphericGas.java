package com.company.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_ATMOSPHERIC_GAS", indexes = {
        @Index(name = "IDX_ST_ATMOSPHERIC_GAS_GAS", columnList = "GAS_ID"),
        @Index(name = "IDX_ST_ATMOSPHERIC_GAS_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "st_AtmosphericGas")
public class AtmosphericGas {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "GAS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Gas gas;

    @PositiveOrZero(message = "{msg://com.company.spacetrans.entity/AtmosphericGas.volume.validation.PositiveOrZero}")
    @Column(name = "VOLUME", precision = 5, scale = 2)
    private BigDecimal volume;
    @JoinColumn(name = "ATMOSPHERE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}