package com.jsatomi.ChallengeLiteratura.repository;

import com.jsatomi.ChallengeLiteratura.model.DatosLibros;
import com.jsatomi.ChallengeLiteratura.model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class LibroService {
    private LibroRepository libroRepository;

    public void guardarLibro(List<DatosLibros> datosLibros) {
        List<Libro> LibroParaGuardar = datosLibros.stream()
                .map(d -> new Libro(d.nombreLibro(),
                        d.nombreAutores(),
                        d.nombreAutores().get(0).fechaNacimiento(),
                        d.nombreAutores().get(0).fechaFallecimiento(),
                        d.idioma(),
                        d.numeroDescargas())).collect(Collectors.toList());
        libroRepository.saveAll(LibroParaGuardar);
    }
}

