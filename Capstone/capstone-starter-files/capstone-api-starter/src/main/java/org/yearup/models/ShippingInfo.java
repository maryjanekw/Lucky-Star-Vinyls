package org.yearup.models;

import java.math.BigDecimal;

public class ShippingInfo {

    private String address;
    private String city;
    private String state;
    private String zip;
    private BigDecimal shippingAmount;

    public ShippingInfo(String address, String city, String state, String zip, BigDecimal shippingAmount) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shippingAmount = shippingAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    @Override
    public String toString() {
        return String.format("ShippingInfo[address=%s, city=%s, state=%s, zip=%s, amount=%s]",
                address, city, state, zip, shippingAmount);
    }

}
