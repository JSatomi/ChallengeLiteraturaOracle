package com.jsatomi.ChallengeLiteratura.repository;

import com.jsatomi.ChallengeLiteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByNombreLibroAndNombreAutor(String titulo, String autor);

    @Query("SELECT DISTINCT l.nombreAutor FROM Libro l ORDER BY l.nombreAutor")
    List<String> mostrarAutores();

    @Query("SELECT DISTINCT l.nombreAutor FROM Libro l " +
            "WHERE (l.fechaFallecimiento IS NULL OR l.fechaFallecimiento >= :ano) " +
            "AND l.fechaNacimiento <= :ano " +
            "ORDER BY l.nombreAutor")
    List<String> mostarAutoresVivosSegunAno(@Param("ano") int ano);


    @Query("SELECT s FROM Libro s WHERE s.idioma LIKE %:idioma% ")
    List<Libro> librosPorIdioma(@Param("idioma") String idioma);
}
