package com.eliseu.BikesRegistration.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Bike id= " + id + " não encontrado no cadastro");
    }

}
