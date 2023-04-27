package com.ivango.quiosque.repository;

import com.ivango.quiosque.entity.Quiosque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuiosqueRepository extends JpaRepository<Quiosque, Long> {
}
