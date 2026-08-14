package com.artemisa.ranafragmentador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultadoRanaResponse
{
    private Long analisisId;
    private int totalFragmentos;
    private Map<String, Integer> firmasRepetidas;
    private List<String> firmasUnicas;
}
