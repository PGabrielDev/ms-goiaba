package io.github.PGabrielDev.msavaliadordecredito.application.exceptions;

import lombok.Getter;

public class ErrorComunicacaoMicroservices extends Exception {
    @Getter
    private int status;

    public ErrorComunicacaoMicroservices(String msg, int status) {
        super(msg);
        this.status = status;
    }
}
