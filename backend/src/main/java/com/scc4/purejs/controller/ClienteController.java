package com.scc4.purejs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc4.purejs.error.MissingProperty;
import com.scc4.purejs.error.NotCreateId;
import com.scc4.purejs.error.NotFound;
import com.scc4.purejs.model.Cliente;
import com.scc4.purejs.response.MessageResponse;
import com.scc4.purejs.service.ClienteService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listAll() {
        return ResponseEntity.ok(clienteService.listAll());
    }

    @PostMapping
    public ResponseEntity<MessageResponse> save(@RequestBody Cliente cliente) throws NotCreateId, MissingProperty {
        clienteService.save(cliente);
        return ResponseEntity.ok(new MessageResponse("Cliente salvo com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Integer id) throws NotFound {
        clienteService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Cliente deletado com sucesso"));
    }
}
