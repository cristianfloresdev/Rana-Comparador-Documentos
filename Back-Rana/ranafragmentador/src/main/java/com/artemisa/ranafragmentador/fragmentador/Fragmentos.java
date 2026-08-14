package com.artemisa.ranafragmentador.fragmentador;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Fragmentos representa una unidad minima de datos binarios
 * dentro del algoritmo Rana. Es inmutable una vez creado
 * sus datos y firma no cambian
 */
@Getter
@AllArgsConstructor
public class Fragmentos
{
    private final byte[] datos;
    private final String firma;
}
