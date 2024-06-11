package gustavo.material.MaterialDeConstrucao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "compras")
public class Compras {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_do_produto")
	protected  String nomeProduto;
	@Column(name = "nome_do_comprador")
	protected String nomeComprador;
	@Column(name = "qtd_compra")
	protected Integer quantidadeCompra;
	@Column(name = "preco_do_produto")
	protected Double precoProduto;
	@Column(name = "valor_total")
	protected Double valorTotal;
	@Column(name = "data_venda")
	LocalDate dataVenda = LocalDate.now();

	public Compras(Produto produto, String comprador, Integer quantidadeComprada){
		this.nomeProduto = produto.nome;
		this.nomeComprador = comprador;
		this.quantidadeCompra = quantidadeComprada;
		this.precoProduto = produto.preco;
		this.valorTotal = produto.preco * quantidadeComprada;
	}

}
