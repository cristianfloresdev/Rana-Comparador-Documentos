package com.artemisa.ranafragmentador.controller;

import com.artemisa.ranafragmentador.dto.ResultadoRanaResponse;
import com.artemisa.ranafragmentador.service.RanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/rana")
@RequiredArgsConstructor
public class RanaController
{
    private final RanaService ranaService;

    @PostMapping("/analizar")
    public ResultadoRanaResponse analizar(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam(defaultValue = "1024") int tamBloque) throws Exception
    {
        return ranaService.analizarArchivo(archivo, tamBloque);
    }
}
