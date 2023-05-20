package com.example.crud.Application;

import com.example.crud.DTO.ClienteDTO;
import com.example.crud.Entity.Cliente;
import com.example.crud.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class ClienteApplication {

    @Autowired private ClienteRepository clienteRepository;

    public ClienteDTO findClientesById(String id){
        ClienteDTO clienteDTO = new ClienteDTO();
        if (id == null || id == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Id inválido para la busqueda");
            return clienteDTO;
        }

        Cliente cliente = this.clienteRepository.findOneById(id);
        if (cliente == null) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("No se encontró al cliente");
            return clienteDTO;
        }

        clienteDTO.setCliente(cliente);
        clienteDTO.setError(false);
        return clienteDTO;
    }

    public ClienteDTO borrarCliente(String id){
        ClienteDTO clienteDTO = new ClienteDTO();
        if (id == null || id == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Id inválido para la busqueda");
            return clienteDTO;
        }

        Cliente cliente = this.clienteRepository.findOneByIdAndStatus(id, "ACTIVO");
        if (cliente == null) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("No se encontró al cliente");
            return clienteDTO;
        }

        cliente.setStatus("INACTIVO");
        LocalDateTime ldt = LocalDateTime.now();

        cliente.setUpdatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
        this.clienteRepository.save(cliente);

        clienteDTO.setCliente(cliente);
        clienteDTO.setMessage("Cliente borrado correctamente");
        clienteDTO.setError(false);
        return clienteDTO;
    }

    public ClienteDTO agregarCliente(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        if (cliente.getNombres() == null || cliente.getNombres() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Nombre inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Apellido Materno inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Apellido Paterno inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Fecha De Nacimiento inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getCurp() == null || cliente.getCurp() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("CURP inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getRfc() == null || cliente.getRfc() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("RFC inválido para agregar");
            return clienteDTO;
        }

        if (cliente.getDomicilio() == null || cliente.getDomicilio() == "") {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Domicilio inválido para agregar");
            return clienteDTO;
        }

        Cliente clienteBDCurp = this.clienteRepository.findOneByCurp(cliente.getCurp());
        Cliente clienteBDRFC = this.clienteRepository.findOneByRfc(cliente.getRfc());
        if (clienteBDCurp != null || clienteBDRFC != null) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("El cliente ya existe");
            return clienteDTO;
        }

        cliente.setStatus("ACTIVO");

        LocalDateTime ldt = LocalDateTime.now();

        cliente.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
        cliente.setUpdatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));

        Cliente clienteTryGuardado = this.clienteRepository.save(cliente);
        if (clienteTryGuardado == null) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("Ocurrió un error inesperado al guardar el cliente");
            return clienteDTO;
        }

        clienteDTO.setCliente(clienteTryGuardado);
        clienteDTO.setMessage("Cliente agregado correctamente");
        clienteDTO.setError(false);
        return clienteDTO;
    }

    public ClienteDTO getAllClientesActivos() {
        ClienteDTO clienteDTO = new ClienteDTO();

        List<Cliente> clientes =this.clienteRepository.findByStatus("ACTIVO");

        clienteDTO.setError(false);
        clienteDTO.setMessage("Clientes Encontrados");
        clienteDTO.setClienteList(clientes);

        return clienteDTO;
    }

    public ClienteDTO actualizarInfoCliente(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        if (cliente.getId() == null || cliente.getId() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Id Invalido");
            return clienteDTO;
        }

        Cliente clienteValidar = this.clienteRepository.findOneByIdAndStatus(cliente.getId(), "ACTIVO");
        if (clienteValidar == null) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("No existe el cliente que se busca actualizar");
            return clienteDTO;
        }

        if (cliente.getNombres() == null || cliente.getNombres() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Nombre Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Apellido Paterno Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Apellido Materno Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Fecha Nacimiento Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getRfc() == null || cliente.getRfc() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("RFC Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getCurp() == null || cliente.getCurp() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("CURP Invalido para actualizar");
            return clienteDTO;
        }

        if (cliente.getDomicilio() == null || cliente.getDomicilio() == ""){
            clienteDTO.setError(true);
            clienteDTO.setMessage("Domicilio Invalido para actualizar");
            return clienteDTO;
        }

        cliente.setStatus("ACTIVO");

        LocalDateTime ldt = LocalDateTime.now();

        cliente.setUpdatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));

        Cliente clienteModificado = this.clienteRepository.save(cliente);

        if (clienteModificado == null ) {
            clienteDTO.setError(true);
            clienteDTO.setMessage("CURP Invalido para actualizar");
            return clienteDTO;
        }

        clienteDTO.setError(false);
        clienteDTO.setMessage("Cliente Actualizado correctamente");
        clienteDTO.setCliente(clienteModificado);
        return clienteDTO;
    }
}
