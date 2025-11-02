package com.mackenzie.e_commerce.repository;

import com.mackenzie.e_commerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
