package com.eliseu.BikesRegistration.service.exceptions;

public class UnnecessaryUpdateException extends RuntimeException {

    public UnnecessaryUpdateException(Object id) {
        super("Bike com id '"+id+"' sem alterações!!!!!!!! Bike não foi atualizada devido a não haver dados a serem atualizados na requisição.");
    }

}
