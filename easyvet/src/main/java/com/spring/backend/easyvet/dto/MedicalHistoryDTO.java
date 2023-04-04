package com.spring.backend.easyvet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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

	public MedicalHistoryDTO(@JsonProperty("id") Long id, @JsonProperty("pet_id") Long petId,
			@JsonProperty("reason_for_consultation") String reasonForConsultation,
			@JsonProperty("background_medical") String backgroundMedical,
			@JsonProperty("medical_formula") String medicalFormula, @JsonProperty("exam_results") String examResults,
			@JsonProperty("vaccines") String vaccines, @JsonProperty("hospital_orders") String hospitalOrders,
			@JsonProperty("nutritional_history") String nutritionalHistory,
			@JsonProperty("observation") String observation, @JsonProperty("surgeries") String surgeries) {
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
	}
}
