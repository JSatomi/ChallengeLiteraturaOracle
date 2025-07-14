package com.jsatomi.ChallengeLiteratura.service;

import com.jsatomi.ChallengeLiteratura.model.Datos;
import com.jsatomi.ChallengeLiteratura.model.DatosLibros;

public class ApiConnectionService {

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private static final String URL_BASE = "https://gutendex.com//books?";
    private static final String URL_BUSQUEDA = "?search=";
    private String URL_USER;

   String json = consumoApi.obtenerDatos(URL_BASE);
//   var datos = convierteDatos.obtenerDatos(json, Datos.class);


}
