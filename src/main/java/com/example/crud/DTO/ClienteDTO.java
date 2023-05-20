package com.example.crud.DTO;

import com.example.crud.Entity.Cliente;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private String message;

    private Boolean error;

    private List<Cliente> clienteList;

    private Cliente cliente;

}
