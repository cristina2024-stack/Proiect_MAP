package com.example.mall_management.model;

import java.util.Objects;

public class Security_Staff extends Staff {

    private String badgeNo;

    public Security_Staff() {
        super();
    }

    public Security_Staff(String id, String name, String badgeNo) {
        super(id, name);
        this.badgeNo = badgeNo;
    }

    public String getBadgeNo() {
        return badgeNo;
    }

    public void setBadgeNo(String badgeNo) {
        this.badgeNo = badgeNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Security_Staff)) return false;
        Security_Staff that = (Security_Staff) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
