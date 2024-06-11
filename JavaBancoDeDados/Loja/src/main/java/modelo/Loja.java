package modelo;

import DAO.CategoriaDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Util.JPAUtil;
import vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Loja {
    EntityManager em = JPAUtil.getEntityManager();
    ProdutoDAO produtoDao = new ProdutoDAO(em);
    CategoriaDAO categoriaDao = new CategoriaDAO(em);

    PedidoDAO pedidoDAO = new PedidoDAO(em);

    Random randvalue = new Random();
    public void cadastroDeProduto(String categoriaTipo, String nomeDoProduto,String descricao, BigDecimal valor){



        Long cod1 = randvalue.nextLong(1, 999);
        Long cod2 = randvalue.nextLong(1, 999);
        Categoria categoria = new Categoria(cod2,categoriaTipo );
        Produto produto = new Produto(cod1, nomeDoProduto, descricao,new BigDecimal(String.valueOf(valor)), categoria);
        em.getTransaction().begin();
        em.persist(produto);
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto );
        em.getTransaction().commit();
        em.clear();
    }
    public void alterarProduto(Long id, String novoNome,String descricao, BigDecimal valor,  String categoriaTipo){
        Produto produto = new Produto();
        Long cod2 = randvalue.nextLong(1, 99999);
        Categoria categoria = new Categoria(cod2, categoriaTipo);
        em.getTransaction().begin();
        produto.setId(id);

        produto = em.merge(produto);
        categoria = em.merge(categoria);
        produtoDao.atualizar(produto);
        categoriaDao.atualizar(categoria);
        produto.setNome(novoNome);
        produto.setPreco(new BigDecimal(String.valueOf(valor)));
        produto.setDescricao(descricao);
        produto.setCategoria(categoria);
        em.flush();
        //em.remove(celular);
        em.getTransaction().commit();
        em.clear();

    }public void apagaProduto(Long prodId, String categoriaNome){
        Produto prod = new Produto();
        Categoria cat = new Categoria();
        em.getTransaction().begin();
        prod.setId(prodId);
        cat.setNome(categoriaNome);
        prod = em.merge(prod);
        cat = em.merge(cat);
        produtoDao.removeProduto(prod);
        categoriaDao.removeCategoria(cat);
        em.getTransaction().commit();
        em.clear();
    }
    public void buscarPorduto(Long id){
        Produto p = produtoDao.buscarId(id);
        System.out.println(p.getPreco());
    }
    public void buscarTodos(){
        List<Produto> todos =  produtoDao.buscarTodos();
        todos.forEach(p -> System.out.println(p.getNome()));

    }
    public void buscarPorNome(String nome){
        List<Produto> todos =  produtoDao.buscarPorNome(nome);
        todos.forEach(p -> System.out.println(p.getNome()));
    }
    public void buscarPorNomeDaCategoria(String nome){
        List<Produto> todos =  produtoDao.buscarPorNomeDaCategoria(nome);
        todos.forEach(p -> System.out.println(p.getNome()));
    }
    public void buscarPorPrecoComNome(String nome){
        BigDecimal preco = produtoDao.buscarPrecoDoProdutoPeloNome(nome);
        System.out.println("Preço do Produto "+nome+ " é R$"+preco);
    }


    public void apresentaRelatorioDeVendas() {
        PedidoDAO pedidoDao = new PedidoDAO(em);
        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);
    }
    public void pesquisa(){
        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1247L);
        System.out.println(pedido.getCliente().getNome());
    }
    public void pesquisaCritreria(String nome, BigDecimal preco, LocalDate dataCadastro){
        List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria(nome, preco, dataCadastro);
      for(Produto produto : produtos){
          System.out.println("Nome - "+produto.getNome()+" Preço - "+produto.getPreco()+" Data de cadastro - "+produto.getDataCadastro()+"\n");
      }
    }
}
