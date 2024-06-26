import java.util.ArrayList;
import java.util.List;


public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected List<String> transacoes;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.transacoes = new ArrayList<>();
	}

	@Override
	public void sacar(double valor) {
		if (saldo >= valor) {
			saldo -= valor;
			transacoes.add(String.format("Saque de %.2f", valor));
		} else {
			System.out.println("Saldo insuficiente para saque");
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		transacoes.add(String.format("Depósito de %.2f", valor));
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if(saldo >= valor){
		this.sacar(valor);
		contaDestino.depositar(valor);
		transacoes.add(String.format("Transferência de %.2f para a conta %d", valor, contaDestino.getNumero()));
		} else {
			System.out.println("Saldo insuficiente para transferência.");
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	public void imprimirTransacoes(){
		System.out.println("\n=== Histórico de transações ===\n");
		for(String transacao : transacoes) {
			System.out.println(transacao);
		}
	}
}