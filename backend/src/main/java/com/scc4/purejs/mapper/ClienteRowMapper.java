package com.scc4.purejs.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.scc4.purejs.model.Cliente;

@Component
public class ClienteRowMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setProfissao(rs.getString("profissao"));
        return cliente;
    }
}
