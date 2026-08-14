package com.artemisa.ranafragmentador.fragmentador;

import com.artemisa.ranafragmentador.util.HashUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragmentador
{
    public static List<Fragmentos> fragmentarArchivo(String ruta, int tamBloque) throws Exception
    {
        byte[] archivo = Files.readAllBytes(Paths.get(ruta));
        List<Fragmentos> fragmentos = new ArrayList<>();

        for(int i = 0; i < archivo.length; i += tamBloque)
        {
            byte[] bloque = Arrays.copyOfRange(archivo, i, Math.min(i + tamBloque, archivo.length));
            String firma = HashUtil.sha256(bloque);
            fragmentos.add(new Fragmentos(bloque, firma));
        }
        return fragmentos;
    }
}
