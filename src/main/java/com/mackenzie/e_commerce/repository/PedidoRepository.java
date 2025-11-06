package com.mackenzie.e_commerce.repository;

import com.mackenzie.e_commerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteEmailOrderByDataCriacaoDesc(String email);
}
