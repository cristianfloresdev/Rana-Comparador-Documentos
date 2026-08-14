package com.artemisa.ranafragmentador.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "analisis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalisisEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArchivo;
    private LocalDateTime fecha;
    private int totalFragmentos;

    @OneToMany(mappedBy = "analisis", cascade = CascadeType.ALL)
    private List<FragmentoEntity> fragmentos = new ArrayList<>();
}
