package com.ivango.quiosque.repository;

import com.ivango.quiosque.entity.Quiosque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuiosqueRepository extends JpaRepository<Quiosque, Long> {
  List<Quiosque> findByNomeLike(String nome);
}
