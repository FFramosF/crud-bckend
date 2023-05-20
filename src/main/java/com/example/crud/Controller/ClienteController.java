package com.example.crud.Controller;

import com.example.crud.Application.ClienteApplication;
import com.example.crud.DTO.ClienteDTO;
import com.example.crud.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

    @Autowired private ClienteApplication clienteApplication;

    @GetMapping(value = "")
    private ClienteDTO getClienteById(
            @RequestParam("id") String id
    ) {
        return this.clienteApplication.findClientesById(id);
    }

    @DeleteMapping(value = "")
    private ClienteDTO deleteCliente( @RequestParam("id") String id) {
        return this.clienteApplication.borrarCliente(id);
    }

    @PostMapping(value="")
    private ClienteDTO addCliente(@RequestBody() Cliente cliente) {
        return this.clienteApplication.agregarCliente(cliente);
    }

    @GetMapping(value="activos")
    private ClienteDTO getClientesActivos() {
        return this.clienteApplication.getAllClientesActivos();
    }

    @PutMapping(value = "")
    private ClienteDTO editarCliente(@RequestBody() Cliente cliente){
        return this.clienteApplication.actualizarInfoCliente(cliente);
    }
}
