package gustavo.material.MaterialDeConstrucao.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected Double preco;
    @Column(name = "qtd_material")
    protected int qtdMaterial;
    @Column(name = "data_de_entrada")
    LocalDate dataEntrada = LocalDate.now();

    public Produto(String nome, Double preco, Integer qtdMaterial){
        this.nome = nome;
        this.preco = preco;
        this.qtdMaterial = qtdMaterial;
    }
    public Produto(Long id, String nome, Double preco, Integer qtdMaterial){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdMaterial = qtdMaterial;
    }

    public Produto(Produto mat) {
        this.id = mat.getId();
        this.nome = mat.getNome();
        this.preco = mat.getPreco();
        this.qtdMaterial = mat.getQtdMaterial();
        this.dataEntrada = LocalDate.now();
    }




}