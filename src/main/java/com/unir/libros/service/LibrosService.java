package com.unir.libros.service;

import java.util.List;

import com.unir.libros.controller.model.CreateLibroRequest;
import com.unir.libros.controller.model.LibroDto;
import com.unir.libros.data.model.Libro;

public interface LibrosService {
	
	List<Libro> getLibros(String titulo, String isbn, String autor, String categoria, Boolean visible, Double valoracion);

	Libro getLibro(String libroId);

	Libro createLibro(CreateLibroRequest request);

	Boolean removeLibro(String libroId);

	Libro updateLibro(String productId, String updateRequest);

	Libro updateLibro(String productId, LibroDto updateRequest);

}
