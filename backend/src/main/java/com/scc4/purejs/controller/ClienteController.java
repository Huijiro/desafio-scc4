package com.scc4.purejs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc4.purejs.mapper.ClienteRowMapper;
import com.scc4.purejs.model.Cliente;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClienteRowMapper clienteRowMapper;

    @GetMapping
    public List<Cliente> listAll() {
        return jdbcTemplate.query("select * from cliente", clienteRowMapper);
    }

    @PostMapping
    public void save(@RequestBody Cliente cliente) {
        jdbcTemplate.update("insert into cliente (nome, email, telefone, profissao) values (?, ?, ?, ?)",
                cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getProfissao());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        jdbcTemplate.update("delete from cliente where id = ?", id);
    }
}
