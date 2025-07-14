package com.jsatomi.ChallengeLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    @JsonAlias("title") String nombreLibro,
    @JsonAlias("authors") List<Autors> nombreAutores,
    @JsonAlias("languages") List<String> idioma,
    @JsonAlias("download_count") Long numeroDescargas

){
    @Override
    public String toString() {
        return String.format("""
                Libro: %s
                Autores: %s
                Idioma: %s
                Descargas: %s
                --------------------
                """,nombreLibro,
                nombreAutores.stream()
                .map(Autors::toString)
                        .collect(Collectors.joining("\n   ")),
                String.join(", ", idioma), numeroDescargas);
    }
}

