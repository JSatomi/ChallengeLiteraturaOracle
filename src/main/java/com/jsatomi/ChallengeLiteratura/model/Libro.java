package com.jsatomi.ChallengeLiteratura.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Libro",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "libro_autor_unico",
                columnNames = {"nombre_libro","nombre_autor"}
        )
    })
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fechaNacimiento, fechaFallecimiento, descargas;

    @Column(unique = true) // Indicamos que no puede crear objetos con titulos repetidos, es decir, que sean unicos
    private String nombreLibro;

    private String nombreAutor, idioma;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.nombreLibro = datosLibros.nombreLibro();

        List<Autors> autores = datosLibros.nombreAutores();
        this.nombreAutor = (autores != null && !autores.isEmpty()) ?
                autores.get(0).nombreAutor() : "Desconocido";
        this.fechaNacimiento = (autores != null && !autores.isEmpty()) ?
                autores.get(0).fechaNacimiento() : 0L;
        this.fechaFallecimiento = (autores != null && !autores.isEmpty()) ?
                autores.get(0).fechaFallecimiento() : 0L;

        // Manejo seguro de idioma (null o vacío)
        List<String> idiomas = datosLibros.idioma();
        this.idioma = (idiomas != null && !idiomas.isEmpty()) ?
                idiomas.get(0) : "N/A";

        this.descargas = datosLibros.numeroDescargas() != null ?
                datosLibros.numeroDescargas() : 0L;

        //  this.nombreAutor = datosLibros.nombreAutor() != null ? datosLibros.nombreAutor() : "Desconocido";
    }

    public Libro(String s, List<Autors> autors, Long aLong, Long aLong1, List<String> idioma, Long aLong2) {
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Long fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Long fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Autor: %s
                Idoma: %s
                Descargas:  %d
                Año nacimiento autor: %d
                Año fallecimiento Autor: %d
                """,
                this.nombreLibro,
                this.nombreAutor,
                this.idioma,
                this.descargas,
                this.fechaNacimiento,
                this.fechaFallecimiento);
    }
}



