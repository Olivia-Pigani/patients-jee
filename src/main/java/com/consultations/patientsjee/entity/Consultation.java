package com.consultations.patientsjee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_consultation", nullable = false)
    private Date dateConsultation;

    @Column(name = "doctor_first_name", nullable = false)
    private String doctorFirstName;

    @Column(name = "doctor_last_name", nullable = false)
    private String doctorLastName;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "medical_form_id")
    private MedicalForm medicalForm;



}
