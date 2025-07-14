package com.jsatomi.ChallengeLiteratura.principal;

import com.jsatomi.ChallengeLiteratura.model.Autors;
import com.jsatomi.ChallengeLiteratura.model.Datos;
import com.jsatomi.ChallengeLiteratura.model.DatosLibros;
import com.jsatomi.ChallengeLiteratura.model.Libro;
import com.jsatomi.ChallengeLiteratura.repository.LibroRepository;
import com.jsatomi.ChallengeLiteratura.service.ConsumoApi;
import com.jsatomi.ChallengeLiteratura.service.ConvierteDatos;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private static final String URL_BASE = "https://gutendex.com//books/";
    private static final String URL_BUSQUEDA = "?search=";
    private String URL_USER;

    private LibroRepository libroRepository;
    private List<Libro> libros;
    //private DatosLibros datosLibros;

    String menu = """
            Elija una opcion a través de su número
            1- Buscar libro por titulo
            2- Listar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos a partir de determiano año
            5- Listar libros por idioma 
            0- salir
            """;

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {

            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiamos el buffer

                switch (opcion) {
                    case 1:
                        //System.out.println("metodo 1\n");
                        buscarLibroPorTitulo();
                        break;
                    case 2:
//                        System.out.println("metodo 2\n");
                        mostrarLibrosBuscados();
                        break;
                    case 3:
//                        System.out.println("metodo 3\n");
                        mostrarLibrosPorAutor();
                        break;
                    case 4:
//                        System.out.println("metodo 4\n");
                        mostrarAutoresVivosSegunAno();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
//                        System.out.println("metodo 5\n");
                        break;
                    case 0:
                        System.out.println("Saliendo...\n");
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("Texto ingresado incorrecto: " + e.getMessage());
                scanner.next();
            }
        }
    }


    private void buscarLibroPorTitulo() {
        DatosLibros datosLibros = getDatosLibros();

        if (datosLibros != null) {
            System.out.println("Libro encontrado");
            System.out.println(datosLibros);

            if (!libroRepository.existsByNombreLibroAndNombreAutor(datosLibros.nombreLibro(),
                    datosLibros.nombreAutores().get(0).nombreAutor())) {
                Libro libro = new Libro(datosLibros);
                libroRepository.save(libro);
                System.out.println("Libro guardado exitosamente");
            } else {
                System.out.println("Libro existente en base de datos");
            }
        }
    }

    private DatosLibros getDatosLibros() {
        System.out.println("Ingresa el nombre del libro que desea buscar");
        URL_USER = scanner.nextLine().replace(" ", "+").toLowerCase();

//        System.out.println(URL_USER);

        var json = consumoApi.obtenerDatos(URL_BASE + URL_BUSQUEDA +
                URL_USER);
        //System.out.println("Respuesta JSON: " + json);

        try {
            Datos respuestaDatos = convierteDatos.obtenerDatos(json, Datos.class);

            if (respuestaDatos.resultadosLibro() == null || respuestaDatos.resultadosLibro().isEmpty()) {
                System.out.println("Libro no encontrado");
                return null;
            }
            return respuestaDatos.resultadosLibro().get(0);

        } catch (Exception e) {
            System.err.println("Error al convertir JSON: " + e.getMessage());
            return null;
        }
    }

    public void mostrarLibrosBuscados() {
        libros = libroRepository.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getDescargas))
                .forEach(System.out::println);
    }

    private void mostrarLibrosPorAutor() {
        List<String> res = libroRepository.mostrarAutores();
        res.forEach(System.out::println);
    }

    private void mostrarAutoresVivosSegunAno() {
        System.out.println("Ingrese el año apartir del cual desea buscar");
        int ano = scanner.nextInt();
        List<String> autores = libroRepository.mostarAutoresVivosSegunAno(ano);
        autores.forEach(System.out::println);
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma en el que desea buscar libros");
        String user = scanner.nextLine();

        List<Libro> res = libroRepository.librosPorIdioma(user);
        res.forEach(System.out::println);
    }
}