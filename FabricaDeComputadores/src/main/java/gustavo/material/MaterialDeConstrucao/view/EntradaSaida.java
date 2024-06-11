package gustavo.material.MaterialDeConstrucao.view;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Random;

public class EntradaSaida {

    public static int solicitaOpcaoDeEntradaNoSoftware(){
        String[] options ={"Produçao", "Comprar", "Sair"};
        return JOptionPane.showOptionDialog(null,"Digite a opcao de uso do sistema","Fabrica LEDD",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    }
    public static String solicitaNomeDoCliente() {
        return JOptionPane.showInputDialog(null, "Escreva seu nome:");
    }
    public static String cadastrarUsuarioLogin(){
        return JOptionPane.showInputDialog("Digite o nome de usuario:");
    }
    public static String cadastrarUsuarioSenha(){
        return JOptionPane.showInputDialog("Digite a senha de usuario:");
    }

    public static int solicitaEscolha() {
        String []opcoes = {"Fabricar Novo Produto","Relatorios","Cadastrar Usuario", "Sair"};
        JComboBox<String>menu = new JComboBox<>(opcoes);
        JOptionPane.showConfirmDialog(null,menu,  "Escolha a opcao que deseja executar", JOptionPane.DEFAULT_OPTION );
        return menu.getSelectedIndex();
    }

    public static int solicitaEscolhaDeLista(){
        String [] option = {"Estoque geral", "Notas Fiscais","Busca produto por nome", "Total Vendido"};
        return JOptionPane.showOptionDialog(null,"Escolha a Lista de Controle ", "Fabrica LEDD", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null , option, option[0] );

    }
    public static int solicitaTipoDepesquisaDeCompra(){
        String [] option = {"Por Id","Por nome"};
        return JOptionPane.showOptionDialog(null,"pesquisa de produto para compra ", "Fabrica LEDD", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null , option, option[0] );

    }

    public static String loginDeEntrada(){
        return JOptionPane.showInputDialog(null, "Digite o usuario", "Login de acesso de usuario", JOptionPane.QUESTION_MESSAGE);
    }
    public static String senhaDeEntrada(String login){
        return JOptionPane.showInputDialog(null, "Usuario :" +login, "Login de acesso de usuario", JOptionPane.QUESTION_MESSAGE);
    }
    public static int solicitaQtdMaterialComprar(){
        return Integer.parseInt(JOptionPane.showInputDialog(null,"Quantidade a ser comprada"));
    }
    public static void msgGeral(String mensagem, int tipoDeMenagem  ){
        JOptionPane.showMessageDialog(null, mensagem, "Fabrica LEDD", tipoDeMenagem);
    }
    public static String solicitaDescricao(){
        return JOptionPane.showInputDialog(null, "Escreava a descrição do produto");
    }
    public static Double solicitaPreco(){
        return Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preco do produto."));
    }
    public static int solicitaQtdMaterial(){
        return Integer.parseInt(JOptionPane.showInputDialog(null,"Quantidade a ser fabricada"));
    }
    public static void listaDeEstoque(String lista){
        JOptionPane.showMessageDialog(null,lista, "Lista em Estoque",JOptionPane.PLAIN_MESSAGE);
    }
    public static void notaFiscalDeCompra(String nota){
        JOptionPane.showMessageDialog(null,nota, "Nota Fiscal da Compra",JOptionPane.PLAIN_MESSAGE);
    }
    public static void listaDeNotas(String lista){
        JOptionPane.showMessageDialog(null,lista, "Notas Fiscais",JOptionPane.PLAIN_MESSAGE);
    }
    public static String retonaNomeProduto(){
        return JOptionPane.showInputDialog(null, "Escreva o nome do produto que deseja comprar");
    }

    public static void  mostraValortTotal(Double soma){
        JOptionPane.showMessageDialog(null, "Valor total das compras é de :"+soma);
    }

    public static Long idPesquisa(){
        return Long.parseLong(JOptionPane.showInputDialog(null, "escreva o id do produto que deseja comprar"));
    }





}
