package com.jsatomi.ChallengeLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autors(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") Long fechaNacimiento,
        @JsonAlias("death_year") Long fechaFallecimiento
) {
    @Override
    public String toString() {
        return String.format("""
                                        
                \tNombre: %s
                \tNacimiento:  %d
                \tFallecimiento: %d """,
                nombreAutor,
                fechaNacimiento,
                fechaFallecimiento);
    }
}
