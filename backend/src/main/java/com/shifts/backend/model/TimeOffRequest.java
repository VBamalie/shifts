package com.shifts.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//TimeOffRequest is a class that represents a time off request. It contains the employee who made the request, the date of the request, and the week day of the request.
public class TimeOffRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade =  CascadeType.ALL, optional = false)
    private Employee employee;
    private int date;
    private weekDayEnum weekDayEnum;
}
