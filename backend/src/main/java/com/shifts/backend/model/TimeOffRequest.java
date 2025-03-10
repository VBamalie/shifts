package com.shifts.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)//employee will persist even when the time off request is deleted
    @JsonIgnore//This prevents a circular reference error when the employee object is serialized.
    private Employee employee;
    private int date;
    private WeekDayEnum weekDayEnum;
}
