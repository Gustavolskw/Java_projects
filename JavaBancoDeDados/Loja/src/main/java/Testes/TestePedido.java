package Testes;

import DAO.ClienteDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Util.JPAUtil;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestePedido {
    public static void main(String[] args) {




        EntityManager em = JPAUtil.getEntityManager();
//        ProdutoDAO produtoDao = new ProdutoDAO(em);
//        Produto celular = produtoDao.buscarId(26L);
//        ClienteDAO clienteDAO = new ClienteDAO(em);
//        Cliente cliente = clienteDAO.buscarClienteId(476L);
        PedidoDAO pedidoDao = new PedidoDAO(em);
        //em.getTransaction().begin();

      //Pedido pedido = new Pedido(784L, cliente);
        //pedido.adicionarItem(new ItemPedido(412L,300,pedido,celular));
       //PedidoDAO pedidoDAO = new PedidoDAO(em);
      // pedidoDAO.cadastrarPedidos(pedido);
      // em.getTransaction().commit();

        //BigDecimal totalVendido=  pedidoDAO.valorTotalVendido();

       //System.out.println("Valor total Vendido é de R$"+totalVendido);
        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);
    }
}
