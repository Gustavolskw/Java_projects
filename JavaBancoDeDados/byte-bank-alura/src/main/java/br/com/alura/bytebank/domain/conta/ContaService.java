package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.ConnectionFactory;
import br.com.alura.bytebank.domain.RegraDeNegocioException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Set;

public class ContaService {
    private ConnectionFactory connection;

    public ContaService(){
        this.connection =  new ConnectionFactory();
    }

    public Set<Conta> listarContasAbertas() {
        Connection conn = connection.recuperaConexao();
        return new ContaDAO(conn).listar();
    }

    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        return conta.getSaldo();
    }

    public void abrir(DadosAberturaConta dadosDaConta) {
        Connection conn = connection.recuperaConexao();
        new ContaDAO(conn).salvar(dadosDaConta);
    }
    private void alterar(Conta conta, BigDecimal valor) {
        Connection conn = connection.recuperaConexao();
        new ContaDAO(conn).alterar(conta.getNumero(), valor);
    }
    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }
        if (!conta.getAtiva()){
            throw new RegraDeNegocioException("conta nao esta ativa");
        }

        BigDecimal novoValor = conta.getSaldo().subtract(valor);
        alterar(conta, novoValor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }
        if (!conta.getAtiva()){
            throw new RegraDeNegocioException("conta nao esta ativa");
        }
        BigDecimal novoValor = conta.getSaldo().add(valor);
        alterar(conta, novoValor);
    }

    public void realizarTransferencia(Integer numeroDaContaOrigem, Integer numeroDaContaDestino, BigDecimal valor){
        this.realizarSaque(numeroDaContaOrigem, valor);
        this.realizarDeposito(numeroDaContaDestino, valor);
    }
    public void encrerrarLogico(Integer numeroDaConta){
        var conta = buscarContaPorNumero(numeroDaConta);
        if(conta.possuiSaldo()){
            throw new RegraDeNegocioException(("Conta não pode ser encerrada por possuir saldo Ativo"));
        }
        Connection conn = connection.recuperaConexao();
        new ContaDAO(conn).encerrarLogico(numeroDaConta);
    }

    public void encerrar(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if(conta.possuiSaldo()){
            throw new RegraDeNegocioException(("Conta não pode ser encerrada por possuir saldo Ativo"));
        }
        Connection conn = connection.recuperaConexao();
        new ContaDAO(conn).deletar(numeroDaConta);
    }

    private Conta buscarContaPorNumero(Integer numero) {
        Connection conn = connection.recuperaConexao();
        Conta conta = new ContaDAO(conn).listarPorNumero(numero);
        if(conta != null){
            return conta;
        }else {
            throw new RegraDeNegocioException("Nao existe Conta Cadastrada com esse numero");
        }
    }
}
