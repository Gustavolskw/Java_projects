package DAO;

import modelo.Cliente;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager em;
    public ClienteDAO (EntityManager em){
        this.em = em;
    }
    public void cadastrarCliente(Cliente cliente){
        this.em.persist(cliente);
    }

    public void removeCliente(Cliente cliente){
        this.em.remove(cliente);
    }
    public Cliente buscarClienteId(Long id){
        return em.find(Cliente.class, id);
    }
}
