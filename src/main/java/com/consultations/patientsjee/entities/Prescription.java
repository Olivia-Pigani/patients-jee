package com.consultations.patientsjee.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pill_type",nullable = false)
    private String pillType;

    @Column(nullable = false)
    private int duration;

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

}
