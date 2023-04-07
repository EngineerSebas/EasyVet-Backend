package com.spring.backend.easyvet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MedicalHistoryDTO {

	private Long id;
	private Long petId;
	private String reasonForConsultation;
	private String backgroundMedical;
	private String medicalFormula;
	private String examResults;
	private String vaccines;
	private String hospitalOrders;
	private String nutritionalHistory;
	private String observation;
	private String surgeries;

	// followup
	private String doctor;
	private String comment;

	// background medical details
	private String diet;
	private String previousDiseases;
	private String recentTreatments;
	private String animalBehavior;

	// medical formula
	private String duration;
	private String diagnostic;
	private String medicine;

	public MedicalHistoryDTO(
			@JsonProperty("id") Long id, 
			@JsonProperty("pet_id") Long petId,
			@JsonProperty("reason_for_consultation") String reasonForConsultation,
			@JsonProperty("background_medical") String backgroundMedical,
			@JsonProperty("medical_formula") String medicalFormula, 
			@JsonProperty("exam_results") String examResults,
			@JsonProperty("vaccines") String vaccines, 
			@JsonProperty("hospital_orders") String hospitalOrders,
			@JsonProperty("nutritional_history") String nutritionalHistory,
			@JsonProperty("observation") String observation,
			@JsonProperty("surgeries") String surgeries,
			@JsonProperty("doctor") String doctor,
			@JsonProperty("comment") String comment,
			@JsonProperty("diet") String diet,
			@JsonProperty("previous_diseases") String previousDiseases,
			@JsonProperty("recent_treatments") String recentTreatments,
			@JsonProperty("animal_behavior") String animalBehavior,
			@JsonProperty("duration") String duration,
			@JsonProperty("diagnostic") String diagnostic,
			@JsonProperty("medicine") String medicine) {
		this.id = id;
		this.petId = petId;
		this.reasonForConsultation = reasonForConsultation;
		this.backgroundMedical = backgroundMedical;
		this.medicalFormula = medicalFormula;
		this.examResults = examResults;
		this.vaccines = vaccines;
		this.hospitalOrders = hospitalOrders;
		this.nutritionalHistory = nutritionalHistory;
		this.observation = observation;
		this.surgeries = surgeries;

		// followup
		this.doctor = doctor;
		this.comment = comment;

		// background medical details
		this.diet = diet;
		this.previousDiseases = previousDiseases;
		this.recentTreatments = recentTreatments;
		this.animalBehavior = animalBehavior;

		// medical formula
		this.duration = duration;
		this.diagnostic = diagnostic;
		this.medicine = medicine;
	}
}
