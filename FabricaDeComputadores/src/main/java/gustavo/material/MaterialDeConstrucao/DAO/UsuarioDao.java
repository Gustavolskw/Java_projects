package gustavo.material.MaterialDeConstrucao.DAO;

import gustavo.material.MaterialDeConstrucao.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioDao {

    EntityManager em;
    public UsuarioDao(EntityManager em){
        this.em = em;
    }
    public void cadastroDeUsuario(Usuario usuario) {
        em.persist(usuario);
    }
    public List<Usuario> buscaPorNome(String nome){
        String jpql = "SELECT u FROM Usuario u WHERE u.login = :nome";


            return em.createQuery(jpql, Usuario.class)
                    .setParameter("nome", nome)
                    .getResultList();




    }
    public List<Usuario> ValidacaoDeSenhaCriteria(String senha, String usuario){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> from = query.from(Usuario.class);
        Predicate filtros = builder.and();
        filtros = builder.and(filtros, builder.equal(from.get("login"),usuario));
        filtros = builder.and(filtros, builder.equal(from.get("senha"),senha));

        query.where(filtros);
        return em.createQuery(query).getResultList();
    }

}
