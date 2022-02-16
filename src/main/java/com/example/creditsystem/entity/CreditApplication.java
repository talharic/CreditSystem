package com.example.creditsystem.entity;

import com.example.creditsystem.enums.CreditApplicationResult;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CREDIT_APPLICATIONS")
@ToString
@RequiredArgsConstructor
public class CreditApplication implements BaseEntity{
    @SequenceGenerator(name = "generator", sequenceName = "CREDIT_APPLICATION_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="nationalIdNumber")
    private User user;

    private LocalDateTime applicationDate;

    private CreditApplicationResult creditApplicationResult;

}
