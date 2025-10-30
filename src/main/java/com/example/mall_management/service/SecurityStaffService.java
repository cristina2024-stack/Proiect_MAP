package com.example.mall_management.service;

import com.example.mall_management.model.Security_Staff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityStaffService {

    private List<Security_Staff> securityStaffList = new ArrayList<>();

    public List<Security_Staff> getAllSecurityStaff() {
        return securityStaffList;
    }

    public Optional<Security_Staff> getSecurityStaffById(String id) {
        for (Security_Staff s : securityStaffList) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public Security_Staff addSecurityStaff(Security_Staff staff) {
        securityStaffList.add(staff);
        return staff;
    }

    public Security_Staff updateSecurityStaff(String id, Security_Staff updatedStaff) {
        for (int i = 0; i < securityStaffList.size(); i++) {
            if (securityStaffList.get(i).getId().equals(id)) {
                securityStaffList.set(i, updatedStaff);
                return updatedStaff;
            }
        }
        // dacă nu există, îl adăugăm
        securityStaffList.add(updatedStaff);
        return updatedStaff;
    }

    public boolean deleteSecurityStaff(String id) {
        for (int i = 0; i < securityStaffList.size(); i++) {
            if (securityStaffList.get(i).getId().equals(id)) {
                securityStaffList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Optional<Security_Staff> findByBadgeNo(String badgeNo) {
        for (Security_Staff s : securityStaffList) {
            if (s.getBadgeNo().equals(badgeNo)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
}
