package gustavo.material.MaterialDeConstrucao.DAO;

import gustavo.material.MaterialDeConstrucao.model.Compras;
import gustavo.material.MaterialDeConstrucao.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;
    public ProdutoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrarProduto(Produto prod){
        this.em.persist(prod);
    }
    public void atualizar(Produto prod){
        this.em.merge(prod);
    }
    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }
    public List<Produto> buscarPorIdValida(Long id){
        String jpql = "SELECT p FROM Produto p WHERE p.id = :id";
        return em.createQuery(jpql, Produto.class).setParameter("id", id).getResultList();
    }

    public List<Produto>listaPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", "%"+nome+"%").getResultList();
    }
    public Produto pesquisaPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", "%"+nome+"%").getSingleResult();
    }







}
