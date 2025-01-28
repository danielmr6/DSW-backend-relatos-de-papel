package com.unir.products.service;

import java.util.List;

import com.unir.products.controller.model.CreateLibroRequest;
import com.unir.products.controller.model.LibroDto;
import com.unir.products.data.model.Libro;

public interface LibrosService {
	
	List<Libro> getLibros(String titulo, String isbn, String autor, String categoria, Boolean visible, Integer valoracion);

	Libro getLibro(String libroId);

	Libro createLibro(CreateLibroRequest request);

	Boolean removeLibro(String libroId);

	Libro updateLibro(String productId, String updateRequest);

	Libro updateLibro(String productId, LibroDto updateRequest);

}
