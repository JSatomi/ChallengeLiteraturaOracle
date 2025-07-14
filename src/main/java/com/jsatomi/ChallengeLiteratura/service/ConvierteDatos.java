package com.jsatomi.ChallengeLiteratura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new  ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            //Deserializamos nuuestro JSON, es decir JSON -> Objeto Java
            //Le enviamos el JSON (json) y la clase a la que lo queremos convertir (clase)
            return objectMapper.readValue(json,clase); // trae un json que va a transformar en la clase que vamos a pasar
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
