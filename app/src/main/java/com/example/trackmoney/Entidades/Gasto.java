package com.example.trackmoney.Entidades;

public class Gasto {
    private int id;
    private int id_type_bill;
    private String type_bill;
    private int id_pay_method;
    private String pay_method;
    private int month;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type_bill() {
        return id_type_bill;
    }

    public void setId_type_bill(int id_type_bill) {
        this.id_type_bill = id_type_bill;
    }

    public String getType_bill() {
        return type_bill;
    }

    public void setType_bill(String type_bill) {
        this.type_bill = type_bill;
    }

    public int getId_pay_method() {
        return id_pay_method;
    }

    public void setId_pay_method(int id_pay_method) {
        this.id_pay_method = id_pay_method;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPay() {
        return pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }

    private Float pay;

}
