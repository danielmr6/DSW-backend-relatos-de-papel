package com.unir.products.data;

import com.unir.products.data.model.Libro;
import com.unir.products.data.utils.Consts;
import com.unir.products.data.utils.SearchCriteria;
import com.unir.products.data.utils.SearchOperation;
import com.unir.products.data.utils.SearchStatement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LibroRepository {

    private final LibroJpaRepository repository;

    public List<Libro> getLibros() {
        return repository.findAll();
    }

    public Libro getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Libro save(Libro libro) {
        return repository.save(libro);
    }

    public void delete(Libro libro) {
        repository.delete(libro);
    }

    public List<Libro> search(String titulo, String isbn, String autor, String categoria, Boolean visible, Integer valoracion) {
        SearchCriteria<Libro> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(titulo)) {
            spec.add(new SearchStatement(Consts.TITULO, titulo, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(isbn)) {
            spec.add(new SearchStatement(Consts.ISBN, isbn, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(autor)) {
            spec.add(new SearchStatement(Consts.AUTOR, autor, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(categoria)) {
            spec.add(new SearchStatement(Consts.CATEGORIA, categoria, SearchOperation.MATCH));
        }

        if (visible != null) {
            spec.add(new SearchStatement(Consts.VISIBLE, visible, SearchOperation.EQUAL));
        }

        if (valoracion != null) {
            spec.add(new SearchStatement(Consts.VALORACION, valoracion, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }

}
