package com.unir.products.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateLibroRequest {
	private String titulo;
	private String isbn;
	private String autor;
	private String categoria;
	private Boolean visible;
}
