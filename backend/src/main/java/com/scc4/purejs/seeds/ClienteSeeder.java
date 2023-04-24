package com.scc4.purejs.seeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.scc4.purejs.mapper.ClienteRowMapper;

@Component
public class ClienteSeeder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        System.out.println("Seeding database...");
        seedCliente();
    }

    private void seedCliente() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from cliente", Integer.class);

        if (count > 0) {
            System.out.println("Table cliente is not empty, seeding not needed.");
            return;
        }

        jdbcTemplate.update("insert into cliente (nome, email, telefone, profissao) values (?, ?, ?, ?)", "João",
                "joao@email.com",
                "999999999", "Programador");

        System.out.println("Table cliente seeded.");
    }
}
