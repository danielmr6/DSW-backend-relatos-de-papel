package com.unir.libros.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.libros.controller.model.CreateLibroRequest;
import com.unir.libros.controller.model.LibroDto;
import com.unir.libros.data.LibroRepository;
import com.unir.libros.data.model.Libro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@Slf4j
public class LibrosServiceImpl implements LibrosService {

	@Autowired
	private LibroRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Libro> getLibros(String titulo, String isbn, String autor, String categoria, Boolean visible, Double valoracion) {

		if (StringUtils.hasLength(titulo) || StringUtils.hasLength(isbn) || StringUtils.hasLength(autor)
				|| StringUtils.hasLength(categoria) || (visible != null) || (valoracion != null)) {
			return repository.search(titulo, isbn, autor, categoria, visible, valoracion);
		}

		List<Libro> libros = repository.getLibros();
		return libros.isEmpty() ? null : libros;
	}

	@Override
	public Libro getLibro(String libroId) {
		return repository.getById(Long.valueOf(libroId));
	}

	@Override
	public Boolean removeLibro(String libroId) {
		Libro libro = repository.getById(Long.valueOf(libroId));

		if (libro != null) {
			repository.delete(libro);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Libro createLibro(CreateLibroRequest request) {
		// Validar que los campos no estén vacíos
		if (request != null &&
				StringUtils.hasLength(request.getTitulo().trim()) &&
				StringUtils.hasLength(request.getIsbn().trim()) &&
				StringUtils.hasLength(request.getCategoria().trim()) &&
				request.getVisible() != null &&
				request.getValoracion() != null) {

			// Si la conversión es exitosa, crear el libro
			Libro libro = Libro.builder()
					.titulo(request.getTitulo())
					.isbn(request.getIsbn())
					.autor(request.getAutor())
					.visible(request.getVisible())
					.categoria(request.getCategoria())
					.valoracion(request.getValoracion())
					.build();

			// Guardar el libro en la base de datos
			return repository.save(libro);

		} else {
			return null; // Si algún campo obligatorio está vacío
		}
	}


	@Override
	public Libro updateLibro(String libroId, String request) {

		// PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Libro libro = repository.getById(Long.valueOf(libroId));
		if (libro != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(libro)));
				Libro patched = objectMapper.treeToValue(target, Libro.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating libro {}", libroId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Libro updateLibro(String libroId, LibroDto updateRequest) {
		Libro libro = repository.getById(Long.valueOf(libroId));
		if (libro != null) {
			libro.update(updateRequest);
			repository.save(libro);
			return libro;
		} else {
			return null;
		}
	}

}
