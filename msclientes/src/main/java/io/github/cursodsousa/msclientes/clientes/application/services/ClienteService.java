package io.github.cursodsousa.msclientes.clientes.application.services;

import io.github.cursodsousa.msclientes.clientes.repository.ClienteRepository;
import io.github.cursodsousa.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {
        return  repository.save(cliente);
    }

    public Optional<Cliente> getbyCPF(String cpf){
        return repository.findByCpf(cpf);
    }

}
