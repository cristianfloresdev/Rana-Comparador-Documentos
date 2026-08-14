package com.artemisa.ranafragmentador.repository;

import com.artemisa.ranafragmentador.entity.AnalisisEntity;
import com.artemisa.ranafragmentador.entity.FragmentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalisisRepository extends JpaRepository<AnalisisEntity, Long>
{
}
