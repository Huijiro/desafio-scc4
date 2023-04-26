package com.scc4.purejs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.scc4.purejs.error.MissingProperty;
import com.scc4.purejs.error.NotCreateId;
import com.scc4.purejs.error.NotFound;
import com.scc4.purejs.model.Cliente;
import com.scc4.purejs.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listAll() {
        return clienteRepository.listAll();
    }

    public void save(Cliente cliente) throws NotCreateId, MissingProperty {
        if (cliente.getId() != null) {
            throw new NotCreateId();
        }

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new MissingProperty("nome");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new MissingProperty("email");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new MissingProperty("telefone");
        }

        if (cliente.getProfissao() == null || cliente.getProfissao().isEmpty()) {
            throw new MissingProperty("profissao");
        }

        clienteRepository.save(cliente);
    }

    public void delete(Integer id) throws NotFound {
        try {
            clienteRepository.delete(id);
        } catch (DataAccessException e) {
            throw new NotFound("Cliente não encontrado");
        }
    }
}
