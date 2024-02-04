package com.consultations.patientsjee.entity;

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
@Table(name = "medical_forms")
public class MedicalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "care_type", nullable = false)
    private String careType;


    private int duration;

    @OneToOne(mappedBy = "medicalForm")
    private Consultation consultation;

}
