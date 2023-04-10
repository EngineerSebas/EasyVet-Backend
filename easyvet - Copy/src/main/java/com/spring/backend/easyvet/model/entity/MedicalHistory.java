package com.spring.backend.easyvet.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet_medical_history")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedicalHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonIgnore
	@Column(name = "pet_id", nullable = false)
	private Long pet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Pet pet;

    private String reasonForConsultation;

    private String backgroundMedical;

    private String medicalFormula;

    private String examResults;

    private String vaccines;

    private String hospitalOrders;

    private String nutritionalHistory;

    private String observation;

    private String surgeries;

    //followup
    private String doctor;

    private String comment;

    @Column(nullable = false)
    private LocalDateTime date;

    //background medical details
    private String diet;

    private String previousDiseases;

    private String recentTreatments;

    private String animalBehavior;

    //medical formula
    private String duration;

    private String diagnostic;

    private String medicine;

    @PrePersist
    public void setCreationDate() {
        this.date = LocalDateTime.now();
    }

}
