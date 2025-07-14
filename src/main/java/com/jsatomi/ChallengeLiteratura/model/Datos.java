package com.jsatomi.ChallengeLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
//        @JsonAlias("count")  Long totalResultados,
//        @JsonAlias("next") String paginaSiguiente,
//        @JsonAlias("previous") String paginaAnterior,
        @JsonAlias("results") List<DatosLibros> resultadosLibro
) {
    @Override
    public String toString() {
        if(resultadosLibro != null && !resultadosLibro.isEmpty()){
            String libros = resultadosLibro.stream()
                    .map(DatosLibros::toString)
                    .collect(Collectors.joining("\n"));
            return """
                    ****RESULTADOS DE TU BUSQUEDA****
                    
                    %s
                    """.formatted(libros);
        }else{
            return "Libro no encontrado";
        }
    }
}
