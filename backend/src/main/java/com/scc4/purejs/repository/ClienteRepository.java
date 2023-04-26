package com.scc4.purejs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.scc4.purejs.mapper.ClienteRowMapper;
import com.scc4.purejs.model.Cliente;

@Repository
public class ClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClienteRowMapper clienteRowMapper;

    public List<Cliente> listAll() {
        return jdbcTemplate.query("select * from cliente", clienteRowMapper);
    }

    public void save(Cliente cliente) {
        jdbcTemplate.update("insert into cliente (nome, email, telefone, profissao) values (?, ?, ?, ?)",
                cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getProfissao());
    }

    public void delete(Integer id) throws DataAccessException {
        jdbcTemplate.update("delete from cliente where id = ?", id);
    }
}
