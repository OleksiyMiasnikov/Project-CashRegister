package com.myProject.dao.entitie;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetails implements Serializable, Comparable<OrderDetails> {
    private long id;
    private Order order;
    private Goods goods;
    private int quantity;
    private double price;

    public OrderDetails() {
    }

    public OrderDetails(long id, Order order, Goods goods, int quantity, double price) {
        this.id = id;
        this.order = order;
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", order=" + order +
                ", goods=" + goods +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(OrderDetails o) {
        return Long.compare(id, o.getId());
    }
}