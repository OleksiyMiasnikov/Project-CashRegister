package com.myProject.entitie;

import java.io.Serializable;
import java.util.Objects;

public class Warehouse implements Serializable, Comparable<Warehouse> {
    private long id;
    private double quantity;
    private Product product;

    public long getId() {
        return id;
    }

    public Warehouse() {
    }

    public Warehouse(long id, double quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public static WarehouseBuilder builder() {
        return new WarehouseBuilder();
    }

    public static class WarehouseBuilder {
        private long id;
        private double quantity;
        private Product product;

        public WarehouseBuilder() {
        }

        public WarehouseBuilder id(long id) {
            this.id = id;
            return this;
        }

        public WarehouseBuilder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public WarehouseBuilder product(Product product) {
            this.product = product;
            return this;
        }
        public Warehouse build() {
            return new Warehouse(id, quantity, product);
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int compareTo(Warehouse o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
