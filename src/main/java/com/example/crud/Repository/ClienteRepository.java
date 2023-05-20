package com.example.crud.Repository;

import com.example.crud.Entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, Integer> {

    Cliente findOneById(@Param("id") String id);

    Cliente findOneByCurp(@Param("curp") String curp);

    Cliente findOneByRfc(@Param("rfc") String rfc);

    List<Cliente> findByStatus(@Param("status") String status);

    Cliente findOneByIdAndStatus(@Param("id") String id, @Param("status") String status);

}
