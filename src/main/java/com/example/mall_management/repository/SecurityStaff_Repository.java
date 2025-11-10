package com.example.mall_management.repository;

import com.example.mall_management.model.Security_Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SecurityStaff_Repository extends InFileRepository<Security_Staff> {

    public SecurityStaff_Repository() {
        super(
                "src/main/resources/data/security_staff.json",
                new EntityAdapter<Security_Staff>() {
                    @Override
                    public String getId(Security_Staff s) { return s.getId(); }

                    @Override
                    public void setId(Security_Staff s, String id) { s.setId(id); }

                    @Override
                    public void validate(Security_Staff s) {

                    }
                }
        );
    }



    public Security_Staff findByName(String name) {
        if (name == null) return null;
        for (Security_Staff staff : findAll()) {
            if (staff.getName() != null && staff.getName().equalsIgnoreCase(name)) {
                return staff;
            }
        }
        return null;
    }

    public Security_Staff findByBadgeNo(String badgeNo) {
        if (badgeNo == null) return null;
        for (Security_Staff staff : findAll()) {
            if (staff.getBadgeNo() != null && staff.getBadgeNo().equalsIgnoreCase(badgeNo)) {
                return staff;
            }
        }
        return null;
    }

    public List<Security_Staff> findWithValidBadge() {
        List<Security_Staff> result = new ArrayList<>();
        for (Security_Staff staff : findAll()) {
            if (staff.getBadgeNo() != null && !staff.getBadgeNo().trim().isEmpty()) {
                result.add(staff);
            }
        }
        return result;
    }
}
