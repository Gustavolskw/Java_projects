package DAO;

import modelo.Categoria;


import javax.persistence.EntityManager;

public class CategoriaDAO {
    private EntityManager em;
    public CategoriaDAO(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }
    public Categoria atualizar(Categoria categoria){
        return  this.em.merge(categoria);
    }
    public void removeCategoria(Categoria categoria){
        this.em.remove(categoria);


    }
}
