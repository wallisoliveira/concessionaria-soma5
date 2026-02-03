package com.lojacarros.concessionaria.repository;

import com.lojacarros.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    // Aqui o Spring Data JPA faz a mágica de criar o SQL sozinho.
    // O método .save() que usamos no Controller vem daqui.
}