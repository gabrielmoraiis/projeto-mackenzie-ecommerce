package com.mackenzie.e_commerce.repository;

import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteEmailOrderByDataCriacaoDesc(String email);

    @Query("SELECT SUM(p.totalPedido) FROM Pedido p WHERE p.status NOT IN ('AGUARDANDO_PAGAMENTO', 'PAGAMENTO_REJEITADO', 'CANCELADO')")
    BigDecimal getReceitaTotal();

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status NOT IN ('AGUARDANDO_PAGAMENTO', 'PAGAMENTO_REJEITADO', 'CANCELADO')")
    Long countPedidosPagos();

    List<Pedido> findByStatusInOrderByDataCriacaoAsc(Collection<StatusPedido> statuses);
}
