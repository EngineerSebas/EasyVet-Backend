package com.spring.backend.easyvet.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

import java.io.Serializable;

/**
 * Entity Role.
 * 
 * @author Andrés.
 */
@Entity
@Table(name = "veterinary")
@NoArgsConstructor
@Getter
@Setter
public class Veterinary extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String priority_rate;

    @Column(nullable = false, length = 60)
    private String general_rate;

    @Column(nullable = false, length = 60)
    private String bank_account;

    @Column(nullable = false, length = 60)
    private String type_bank;
    
    @JsonIgnore
	@Column(name = "specialization_id", nullable = false)
	private Long specialization_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "specialization_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Specialization specialization;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "veterinary_status", nullable = false)
    private EVeterinaryStatus veterinary_status;
    
    public Veterinary(String email, String password) {
        super(email, password);
    }
    
}
