package com.eliseu.BikesRegistration.service.exceptions;

public class ResourceFoundException extends RuntimeException {

    public ResourceFoundException(Object description) {
        super("Bike com description '"+description+"' já existente!!!!!!!! Bike não foi cadastrada");
    }

}
