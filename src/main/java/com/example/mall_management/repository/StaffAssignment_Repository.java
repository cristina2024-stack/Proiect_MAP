package com.example.mall_management.repository;

import com.example.mall_management.model.StaffAssignment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffAssignment_Repository extends InFileRepository<StaffAssignment> {

    public StaffAssignment_Repository() {
        super(
                "src/main/resources/data/staff_assignment.json",
                new EntityAdapter<StaffAssignment>() {
                    @Override
                    public String getId(StaffAssignment a) {
                        return a.getId();
                    }

                    @Override
                    public void setId(StaffAssignment a, String id) {
                        a.setId(id); // asigură-te că modelul are setId()
                    }

                    @Override
                    public void validate(StaffAssignment a) {
                        // Validări opționale
                        // if (a.getShift() == null) throw new IllegalArgumentException("Shift cannot be null");
                    }
                }
        );
    }


    public List<StaffAssignment> findByFloorId(String floorId) {
        List<StaffAssignment> result = new ArrayList<>();
        for (StaffAssignment a : findAll()) {
            if (a.getFloorId() != null && a.getFloorId().equals(floorId)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<StaffAssignment> findByStaffId(String staffId) {
        List<StaffAssignment> result = new ArrayList<>();
        for (StaffAssignment a : findAll()) {
            if (a.getStaffId() != null && a.getStaffId().equals(staffId)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<StaffAssignment> findByShift(StaffAssignment.Shift shift) {
        List<StaffAssignment> result = new ArrayList<>();
        for (StaffAssignment a : findAll()) {
            if (a.getShift() == shift) {
                result.add(a);
            }
        }
        return result;
    }

    public StaffAssignment findByStaffAndShift(String staffId, StaffAssignment.Shift shift) {
        for (StaffAssignment a : findAll()) {
            if (a.getStaffId() != null
                    && a.getStaffId().equals(staffId)
                    && a.getShift() == shift) {
                return a;
            }
        }
        return null;
    }
}
