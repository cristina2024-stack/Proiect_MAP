package com.example.mall_management.model;
public class Security_Staff extends Staff {
    private String badgeNo;

    public Security_Staff(String id, String name, String badgeNo) {
        super(id, name);
        this.badgeNo = badgeNo;
    }

    public String getBadgeNo() { return badgeNo; }
}

