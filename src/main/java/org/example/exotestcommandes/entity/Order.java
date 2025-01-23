package org.example.exotestcommandes.entity;

import java.util.Objects;

public class Order {
    private int id;
    private String item;
    private static int count = 0;


    public Order() {
    }

    public Order(String item) {
        this.item = item;
        id = ++count;
    }

    public Order(int id, String item) {
        this.id = id;
        this.item = item;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(item, order.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item);
    }
}
