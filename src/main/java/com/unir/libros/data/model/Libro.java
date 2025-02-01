package com.unir.libros.data.model;

import com.unir.libros.controller.model.LibroDto;
import com.unir.libros.data.utils.Consts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "libros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = Consts.TITULO, unique = true)
	private String titulo;
	
	@Column(name = Consts.ISBN)
	private String isbn;
	
	@Column(name = Consts.AUTOR)
	private String autor;
	
	@Column(name = Consts.VISIBLE)
	private Boolean visible;

	@Column(name = Consts.CATEGORIA)
	private String categoria;

	// Valoración de 1 a 5
	@Column(name = "valoracion", nullable = false)
	@Min(1)  // Validación: mínimo 1
	@Max(5)  // Validación: máximo 5
	private Double valoracion;

	public void update(LibroDto libroDto) {
		this.titulo = libroDto.getTitulo();
		this.isbn = libroDto.getIsbn();
		this.autor = libroDto.getAutor();
		this.visible = libroDto.getVisible();		
		this.categoria = libroDto.getCategoria();
		this.valoracion = libroDto.getValoracion();
	}
}
