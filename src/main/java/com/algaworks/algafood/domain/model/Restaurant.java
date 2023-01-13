package com.algaworks.algafood.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Long id;

    @Column(name = "nm_restaurant", nullable = false)
    private String name;

    @Column(name = "vl_delivery_tax", nullable = false)
    private BigDecimal deliveryTax;

    @ManyToOne
    @JoinColumn(name = "id_kitchen", nullable = false)
    private Kitchen kitchen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public BigDecimal getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(BigDecimal deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
