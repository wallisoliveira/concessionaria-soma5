package com.lojacarros.concessionaria.repository;

import com.lojacarros.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    // Aqui o Spring já nos entrega métodos como save(), findAll(), delete()
}