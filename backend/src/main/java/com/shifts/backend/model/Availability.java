package com.shifts.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int availabilityId;
    private int employeeId;
    private double m_start;
    private double m_end;
    private double t_start;
    private double t_end;
    private double w_start;
    private double w_end;
    private double th_start;
    private double th_end;
    private double f_start;
    private double f_end;
    private double s_start;
    private double s_end;
    private double su_start;
    private double su_end;
}
