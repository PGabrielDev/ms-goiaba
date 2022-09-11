package io.github.PGabrielDev.msavaliadordecredito.application.exceptions;

public class DadosClienteNotFoundExeption extends Exception {
    public DadosClienteNotFoundExeption(String cpf) {
        super("Não foram entrado dados para o cliente com cpf: " + cpf);
    }
}
