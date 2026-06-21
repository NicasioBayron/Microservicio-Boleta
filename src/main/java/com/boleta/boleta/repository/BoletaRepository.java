package com.boleta.boleta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boleta.boleta.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
}
