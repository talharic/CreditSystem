package com.example.creditsystem.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "CREDIT_SCORE")
@Data
@ToString
@RequiredArgsConstructor
public class CreditScore implements BaseEntity {

    @SequenceGenerator(name = "generator", sequenceName = "CREDIT_SCORE_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    private String nationalIdNumber;

    private Long score;
}
