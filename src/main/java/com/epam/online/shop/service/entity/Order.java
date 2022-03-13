package com.epam.online.shop.service.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order  {
    private Long id;
    private Date orderDate;
    private Integer userId;
    private Status status;
    private Double totalPrice;
    private String deliveryAddress;
    private List<OrderDetail> orderDetailList;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderDate, order.orderDate) && Objects.equals(userId, order.userId) && Objects.equals(status, order.status) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(orderDetailList, order.orderDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, userId, status, totalPrice, deliveryAddress, orderDetailList);
    }
}
