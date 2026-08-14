package com.artemisa.ranafragmentador.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fragmentos", indexes = @Index(name = "idx_firma", columnList = "firma"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FragmentoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firma;

    @Column(nullable = false)
    private int orden;

    @ManyToOne
    @JoinColumn(name = "analisis_id")
    private AnalisisEntity analisis;
}
