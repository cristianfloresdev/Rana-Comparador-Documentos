package com.artemisa.ranafragmentador.repository;

import com.artemisa.ranafragmentador.entity.FragmentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FragmentoRepository extends JpaRepository<FragmentoEntity, Long>
{
    List<FragmentoEntity> findByFirma(String firma);
}
