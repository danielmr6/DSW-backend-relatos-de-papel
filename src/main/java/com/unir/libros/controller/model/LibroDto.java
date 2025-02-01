package com.unir.libros.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LibroDto {
	private Long libroId;
	private String titulo;
	private String isbn;
	private Boolean visible;
	private Double valoracion;
	private String autor;
	private String categoria;
}
