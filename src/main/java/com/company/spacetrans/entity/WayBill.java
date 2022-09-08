package com.company.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_WAY_BILL", indexes = {
        @Index(name = "IDX_ST_WAY_BILL_SHIPPER", columnList = "SHIPPER_ID"),
        @Index(name = "IDX_ST_WAY_BILL_CONSIGNEE", columnList = "CONSIGNEE_ID"),
        @Index(name = "IDX_ST_WAY_BILL_DEPARTURE_PORT", columnList = "DEPARTURE_PORT_ID"),
        @Index(name = "IDX_ST_WAY_BILL_DESTINATION_PORT", columnList = "DESTINATION_PORT_ID"),
        @Index(name = "IDX_ST_WAY_BILL_CARRIER", columnList = "CARRIER_ID")
})
@Entity(name = "st_WayBill")
public class WayBill {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "REFERENCE", nullable = false)
    @NotNull
    private String reference;

    @JoinColumn(name = "SHIPPER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer shipper;

    @JoinColumn(name = "CONSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer consignee;

    @JoinColumn(name = "DEPARTURE_PORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpacePort departurePort;

    @JoinColumn(name = "DESTINATION_PORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpacePort destinationPort;

    @JoinColumn(name = "CARRIER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrier carrier;

    @Composition
    @OneToMany(mappedBy = "wayBill")
    private List<WayBillItem> items;

    @Column(name = "TOTAL_WEIGHT")
    private Double totalWeight;

    @Column(name = "TOTAL_CHARGE", precision = 19, scale = 2)
    private BigDecimal totalCharge;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setItems(List<WayBillItem> items) {
        this.items = items;
    }

    public List<WayBillItem> getItems() {
        return items;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public SpacePort getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(SpacePort destinationPort) {
        this.destinationPort = destinationPort;
    }

    public SpacePort getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(SpacePort departurePort) {
        this.departurePort = departurePort;
    }

    public Customer getConsignee() {
        return consignee;
    }

    public void setConsignee(Customer consignee) {
        this.consignee = consignee;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}