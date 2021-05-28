package com.tfd.oxangel.Models;

public class UserCompany {
    String id;
    String razonSocial;
    String emailCoporativo;
    String phone;
    String ruc;
    String direction;

    public UserCompany() {
    }

    public UserCompany(String id, String razonSocial, String emailCoporativo, String phone, String ruc, String direction) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.emailCoporativo = emailCoporativo;
        this.phone = phone;
        this.ruc = ruc;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmailCoporativo() {
        return emailCoporativo;
    }

    public void setEmailCoporativo(String emailCoporativo) {
        this.emailCoporativo = emailCoporativo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
