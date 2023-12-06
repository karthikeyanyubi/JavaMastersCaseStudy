package com.example.casestudy.model;

import com.example.casestudy.constants.enums.BusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;

    @Column(unique = true, name = "bus_reg_number")
    @NotBlank(message = "Bus registration number is mandatory")
    private String regNumber;

    @Column(name = "bus_type")
    @Enumerated(EnumType.STRING)
    private BusType busType;

    // getters and setters
}
