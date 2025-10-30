
package com.example.mall_management.repository;

import com.example.mall_management.model.Security_Staff;
import java.util.ArrayList;
import java.util.List;

public class SecurityStaff_Repository extends InMemoryRepository<Security_Staff> {

    public SecurityStaff_Repository() {
        super(Security_Staff.class);
    }

    public Security_Staff findByName(String name) {
        List<Security_Staff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            Security_Staff staff = allStaff.get(i);
            if (staff.getName().equalsIgnoreCase(name)) {
                return staff;
            }
        }

        return null;
    }

    public Security_Staff findByBadgeNo(String badgeNo) {
        List<Security_Staff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            Security_Staff staff = allStaff.get(i);
            if (staff.getBadgeNo() != null && staff.getBadgeNo().equalsIgnoreCase(badgeNo)) {
                return staff;
            }
        }

        return null;
    }

    public List<Security_Staff> findWithValidBadge() {
        List<Security_Staff> result = new ArrayList<Security_Staff>();
        List<Security_Staff> allStaff = findAll();

        for (int i = 0; i < allStaff.size(); i++) {
            Security_Staff staff = allStaff.get(i);
            if (staff.getBadgeNo() != null && !staff.getBadgeNo().trim().isEmpty()) {
                result.add(staff);
            }
        }

        return result;
    }
}
