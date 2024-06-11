package gustavo.material.MaterialDeConstrucao.controller;
import gustavo.material.MaterialDeConstrucao.model.*;
import gustavo.material.MaterialDeConstrucao.view.EntradaSaida;

import javax.swing.*;


public class Controller {

    Fabrica fabrica = null;
    Loja loja = null;

    public void start() {
        this.fabrica = new Fabrica();
        this.loja = new Loja();
        int entradaSoftware;
        int opcoesProducao;
        int opcoesRealtorios;

        do {
            //entrada no Softwere
            entradaSoftware=EntradaSaida.solicitaOpcaoDeEntradaNoSoftware();
            switch (entradaSoftware){
                case 0:

                    //executar Login
                    loginUsuario();
                    EntradaSaida.msgGeral("Seja bem vindo(a) a fabrica da Ledd Computadores", JOptionPane.INFORMATION_MESSAGE);
                    do {
                        // ****** Produzir *******
                        opcoesProducao = EntradaSaida.solicitaEscolha();
                        switch(opcoesProducao){
                            case 0 :

                                // ******* Cadastro de Cliente *******
                                String nome = EntradaSaida.solicitaDescricao();
                                Double preco = EntradaSaida.solicitaPreco();
                                Integer qtdFabricar = EntradaSaida.solicitaQtdMaterial();

                                Produto produtoFabricar = new Produto(nome, preco, qtdFabricar);

                                this.fabrica.fabricarProduto(produtoFabricar);
                                break;

                            case 1:
                                //******* relatorios *******
                                opcoesRealtorios = EntradaSaida.solicitaEscolhaDeLista();
                                switch(opcoesRealtorios){
                                    case 0:
                                        // ******* Estoque Total *******
                                        EntradaSaida.listaDeEstoque(this.fabrica.estoqueGeral());
                                        break;
                                    case 1:
                                        //******* todas as notas de compra *******
                                        EntradaSaida.listaDeNotas(this.fabrica.notasFiscais());
                                        break;
                                    case 2:
                                        //******* pesquisa por nome do produto *******
                                        String pesquisa = EntradaSaida.retonaNomeProduto();
                                        EntradaSaida.listaDeEstoque(this.fabrica.estoquePorNome(pesquisa));
                                        break;
                                    case 3:
                                        // ******* Total Comprado *******
                                        EntradaSaida.mostraValortTotal(this.loja.totalComprado());
                                        break;
                                }
                                break;
                            case 2:
                                // ******* caastro de usuarios *******
                                String userCadastro = EntradaSaida.cadastrarUsuarioLogin();
                                String passwordcadastro = EntradaSaida.cadastrarUsuarioSenha();
                                Usuario usuarioCadastro = new Usuario(userCadastro,passwordcadastro);
                                this.fabrica.cadastraUsuario(usuarioCadastro);
                                break;
                        }

                    }while (opcoesProducao!=3);
                    break;
                case 1:
                    EntradaSaida.msgGeral("Seja bem vindo a Loja de Fabrica da LEDD", -1);
                    //solicita produto para comprar por id ou nome;

                    EntradaSaida.listaDeEstoque(this.fabrica.listaDeMateriaisEmEstoque());
                    Long id =EntradaSaida.idPesquisa();
                    this.loja.buscaProdutoPorId(id);
                    Produto produtoComprado = this.loja.buscaPordutoEntPorId(id);
                    String nomeDoCliente = EntradaSaida.solicitaNomeDoCliente();
                    int qtdComprada = EntradaSaida.solicitaQtdMaterialComprar();
                    Compras pedido = new Compras(produtoComprado, nomeDoCliente, qtdComprada);
                    this.loja.cadastraPedido(pedido);
                    int qtdEstoqueAtualizado = produtoComprado.getQtdMaterial() - qtdComprada;
                    Produto prodtAtualizado = new Produto(produtoComprado.getId(), produtoComprado.getNome(), produtoComprado.getPreco(),qtdEstoqueAtualizado);
                    this.fabrica.atualizaEstoque(prodtAtualizado);
                    EntradaSaida.notaFiscalDeCompra(this.loja.buscaProdutoPorId(pedido.getId()));


                    //realizar nota fiscal e devolver ao cliente a impressa da nota;
            }

        } while (entradaSoftware!=2);
        EntradaSaida.msgGeral("Encerrando...", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);



    }

    public void loginUsuario() {
        String usuario;
        String senha;

        usuario = EntradaSaida.loginDeEntrada();
        while (!fabrica.verificaUsuario(usuario)) {
            EntradaSaida.msgGeral("Usuario Não cadastrado", JOptionPane.WARNING_MESSAGE);
            usuario = EntradaSaida.loginDeEntrada();
        }
        senha = EntradaSaida.senhaDeEntrada(usuario);
        while (!fabrica.verificaSenha(senha, usuario)) {
            EntradaSaida.msgGeral("Senha errada", JOptionPane.WARNING_MESSAGE);
            senha = EntradaSaida.senhaDeEntrada(usuario);
        }
    }
}
