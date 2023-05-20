package com.example.crud.Entity;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Entity
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    private String nombres;

    private String apellidoMaterno;

    private String apellidoPaterno;

    private String fechaNacimiento;

    private String rfc;

    private String curp;

    private String domicilio;

    private String status;

    private String createdAt;

    private String updatedAt;

}
