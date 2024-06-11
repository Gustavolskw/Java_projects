package gustavo.material.MaterialDeConstrucao.model;

import gustavo.material.MaterialDeConstrucao.DAO.ComprasDao;
import gustavo.material.MaterialDeConstrucao.DAO.ProdutoDao;
import gustavo.material.MaterialDeConstrucao.DAO.UsuarioDao;
import gustavo.material.MaterialDeConstrucao.Util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Fabrica {
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDao produtoDao = new ProdutoDao(em);
	UsuarioDao usuarioDao = new UsuarioDao(em);
	ComprasDao comprasDao = new ComprasDao(em);
	public void fabricarProduto(Produto prod){
		em.getTransaction().begin();
		produtoDao.cadastrarProduto(prod);
		em.getTransaction().commit();
		em.clear();
	}

	public void cadastraUsuario(Usuario usuario){
		em.getTransaction().begin();
		usuarioDao.cadastroDeUsuario(usuario);
		em.getTransaction().commit();
		em.clear();
	}
	public boolean verificaUsuario(String nome){
		List<Usuario> listaDeUsuarios = usuarioDao.buscaPorNome(nome);
		listaDeUsuarios.forEach(p-> System.out.println(p.getLogin()));
		return !listaDeUsuarios.isEmpty();
	}
	public boolean verificaSenha(String senha, String usuario){
		List<Usuario> listaDeSenhas = usuarioDao.ValidacaoDeSenhaCriteria(senha, usuario);
		listaDeSenhas.forEach(p -> System.out.println(p.getSenha()+p.getLogin()));
		return !listaDeSenhas.isEmpty();
	}

	public String listaDeMateriaisEmEstoque(){
		String retorno = "";
		List<Produto> listaDeEstoque = produtoDao.buscarTodos();
		for (Produto produto :listaDeEstoque){

			retorno += "Codigo: "+produto.getId()+" | Nome Do produto: "+produto.getNome()+" | Preço: "+produto.getPreco()+" | QtdMaterial: "+produto.getQtdMaterial()+"\n";
		}
		return retorno;
	}

	public String estoqueGeral(){
		String retorno ="";
		List<Produto> listaDeProdutos = produtoDao.buscarTodos();
		for (Produto produto : listaDeProdutos){
			retorno += "Codigo: "+produto.getId()+" | Nome do Produto: "+produto.getNome()+" | Preço: "+produto.getPreco()+" | QtdMaterial: "+produto.getQtdMaterial()+" | Data de Entrada: "+produto.getDataEntrada()+"\n";
		}
		return retorno;
	}
	public boolean validaCodigoProdt(Long id){
		List<Produto> listaDeCodigos = produtoDao.buscarPorIdValida(id);
		return !listaDeCodigos.isEmpty();

	}
	public String notasFiscais(){
		StringBuilder retorno = new StringBuilder();
		List<Compras> listaDeNotas = comprasDao.listaDePedidos();
		for (Compras compra : listaDeNotas){
			retorno.append(compra.getId()).append(" | Produto: ").append(compra.getNomeProduto()).append(" | Comprador: ").append(compra.getNomeComprador())
					.append(" | Quantidade Comprada: ").append(compra.getQuantidadeCompra()).append("\n").append(" | preço Unitario ").append(compra.getPrecoProduto())
					.append(" | Valor da Compra: ").append(compra.getValorTotal()).append(" | Data da Compra: ").append(compra.getDataVenda())
					.append("\n").append("-----------------------------------------------------------------------------------").append("\n");
		}
		return retorno.toString();
	}

	public String estoquePorNome(String pesquisa){
		String retorno ="";
		List<Produto> listaDeProdutos = produtoDao.listaPorNome(pesquisa);
		for (Produto produto : listaDeProdutos){
			retorno += "Codigo: "+produto.getId()+" | Nome do Produto: "+produto.getNome()+" | Preço: "+produto.getPreco()+" | QtdMaterial: "+produto.getQtdMaterial()+" | Data de Entrada: "+produto.getDataEntrada()+"\n";
		}
		return retorno;
	}

	public void atualizaEstoque(Produto produto) {
		em.getTransaction().begin();
		produtoDao.atualizar(produto);
		em.getTransaction().commit();
		em.clear();
	}
}
