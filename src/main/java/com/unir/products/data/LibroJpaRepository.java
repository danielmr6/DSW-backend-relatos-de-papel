package com.unir.products.data;

import java.util.List;

import com.unir.products.data.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface LibroJpaRepository extends JpaRepository<Libro, Long>, JpaSpecificationExecutor<Libro> {

	List<Libro> findByTitulo(String titulo);

	List<Libro> findByIsbn(String isbn);

	List<Libro> findByAutor(String autor);

	List<Libro> findByVisible(Boolean visible);
}
