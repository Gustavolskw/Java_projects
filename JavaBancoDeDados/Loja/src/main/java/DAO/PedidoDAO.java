package DAO;

import modelo.Produto;
import vo.RelatorioDeVendasVo;
import modelo.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class PedidoDAO {
    private EntityManager em;
    public PedidoDAO (EntityManager em){
        this.em = em;
    }
    public void cadastrarPedidos(Pedido pedido){
        this.em.persist(pedido);
    }
    public void atualizarPedidos(Pedido pedido){
        this.em.merge(pedido);
    }
    public void removePedidos(Pedido pedido){
        this.em.remove(pedido);
    }
    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new vo.RelatorioDeVendasVo("
                + "produto.nome, "
                + "SUM(item.quantidade) AS quantidadeTotal, "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY quantidadeTotal DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();
    }
    public Pedido buscarPedidoComCliente(Long id){
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }



}


