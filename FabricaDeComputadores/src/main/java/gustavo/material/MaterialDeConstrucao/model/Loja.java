package gustavo.material.MaterialDeConstrucao.model;

import gustavo.material.MaterialDeConstrucao.DAO.ComprasDao;
import gustavo.material.MaterialDeConstrucao.DAO.ProdutoDao;
import gustavo.material.MaterialDeConstrucao.DAO.UsuarioDao;
import gustavo.material.MaterialDeConstrucao.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {
    EntityManager em = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(em);
    ComprasDao comprasDao = new ComprasDao(em);

    public Double totalComprado(){
        List<Compras> todasCompras = comprasDao.listaDePedidos();
        DoubleSummaryStatistics estatisticas = todasCompras.stream().collect(Collectors.summarizingDouble(Compras::getValorTotal));
        return estatisticas.getSum();
    }

    public String buscaProdutoPorId(Long id){
        String retorno ="";
        Produto produto = produtoDao.buscarPorId(id);

        retorno += "Codigo: "+produto.getId()+" | Nome do Produto: "+produto.getNome()+" | Preço: "+produto.getPreco()+" | QtdMaterial: "+produto.getQtdMaterial()+" | Data de Entrada: "+produto.getDataEntrada()+"\n";

        return retorno;
    }


    public Produto buscaPordutoEntPorId(Long id){
        return produtoDao.buscarPorId(id);
    }
    public void cadastraPedido(Compras pedido){
            em.getTransaction().begin();
            comprasDao.cadastrarPedido(pedido);
            em.getTransaction().commit();
            em.clear();
    }

}
