package com.shifts.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Availability is a class that represents a single employee's availability. It contains the employee's availability for each day of the week. it has a one to one relationship with the employee class.
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JsonIgnore//This prevents a circular reference error when the employee object is serialized.
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    
    private double mon_start;
    private double mon_end;
    private double tue_start;
    private double tue_end;
    private double wed_start;
    private double wed_end;
    private double thu_start;
    private double thu_end;
    private double fri_start;
    private double fri_end;
    private double sat_start;
    private double sat_end;
    private double sun_start;
    private double sun_end;
}
