package com.spring.backend.easyvet.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;
    
    public Veterinary(String email, String password) {
        super(email, password);
    }
    
}
