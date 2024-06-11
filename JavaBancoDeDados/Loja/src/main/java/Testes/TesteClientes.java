package Testes;

import DAO.ClienteDAO;
import Util.JPAUtil;
import modelo.Cliente;

import javax.persistence.EntityManager;

public class TesteClientes {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Cliente cliente = new Cliente("marcos", "56524198",485L);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();
        clienteDAO.cadastrarCliente(cliente);
        em.getTransaction().commit();
        em.close();



    }
}
