package com.artemisa.ranafragmentador.service;

import com.artemisa.ranafragmentador.dto.ResultadoRanaResponse;
import com.artemisa.ranafragmentador.entity.AnalisisEntity;
import com.artemisa.ranafragmentador.entity.FragmentoEntity;
import com.artemisa.ranafragmentador.fragmentador.Fragmentador;
import com.artemisa.ranafragmentador.fragmentador.Fragmentos;
import com.artemisa.ranafragmentador.repository.AnalisisRepository;
import com.artemisa.ranafragmentador.repository.FragmentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RanaService
{
    private final FragmentoRepository fragmentoRepo;
    private final AnalisisRepository analisisRepo;

    public ResultadoRanaResponse analizarArchivo(MultipartFile archivo, int tamBloque) throws Exception
    {
        // Guardamos temporalmente el archivo subido
        File temp = File.createTempFile("rana_", "_" + archivo.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(temp))
        {
            fos.write(archivo.getBytes());
        }

        //Fragmentamos
        List<Fragmentos> fragmentos = Fragmentador.fragmentarArchivo(temp.getAbsolutePath(), tamBloque);

        //Se crea el registro de analisis
        AnalisisEntity analisis = new AnalisisEntity();
        analisis.setNombreArchivo(archivo.getOriginalFilename());
        analisis.setFecha(LocalDateTime.now());
        analisis.setTotalFragmentos(fragmentos.size());
        analisis = analisisRepo.save(analisis);

        //comparamos cada fragmento contra el histórico y guardamos
        Map<String, Integer> repetidosHistorico = new HashMap<>();
        Set<String> unicos = new HashSet<>();

        for(int i = 0; i < fragmentos.size(); i++)
        {
            Fragmentos f = fragmentos.get(i);

            List<FragmentoEntity> existentes = fragmentoRepo.findByFirma(f.getFirma());

            if(!existentes.isEmpty())
            {
                repetidosHistorico.merge(f.getFirma(), 1, Integer::sum);
            }
            else
            {
                unicos.add(f.getFirma());
            }

            FragmentoEntity entity = new FragmentoEntity();
            entity.setFirma(f.getFirma());
            entity.setOrden(i);
            entity.setAnalisis(analisis);
            fragmentoRepo.save(entity);
        }

        temp.delete();

        return new ResultadoRanaResponse(
                analisis.getId(),
                fragmentos.size(),
                repetidosHistorico,
                new ArrayList<>(unicos)
        );
    }
}
