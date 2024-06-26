import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco("CabozBank");

		Scanner entrada = new Scanner(System.in);

		boolean executando = true;

		while (executando) {
			System.out.println("\n=== Banco Digital ===\n");
			System.out.println("1. Criar Conta Corrente");
			System.out.println("2. Criar Conta Poupança");
			System.out.println("3. Depositar");
			System.out.println("4. Sacar");
			System.out.println("5. Transferir");
			System.out.println("6. Imprimir Extrato");
			System.out.println("7. Sair");
			System.out.print("\nEscolha uma opção: ");
			int opcao = entrada.nextInt();
			entrada.nextLine();

			switch (opcao) {
				case 1:
					System.out.print("Nome do cliente: ");
					String nomeClienteCC = entrada.nextLine();
					Cliente clienteCC = new Cliente();
					clienteCC.setNome(nomeClienteCC);
					Conta contaCorrente = new ContaCorrente(clienteCC);
					banco.adicionarConta(contaCorrente);
					System.out.println("Conta Corrente criada com sucesso!");
					System.out.println("O número da sua conta é: "+ contaCorrente.getNumero());
					break;
				case 2:
					System.out.print("Nome do cliente: ");
					String nomeClienteCP = entrada.nextLine();
					Cliente clienteCP = new Cliente();
					clienteCP.setNome(nomeClienteCP);
					Conta contaPoupanca = new ContaPoupanca(clienteCP);
					banco.adicionarConta(contaPoupanca);
					System.out.println("Conta Poupança criada com sucesso!");
					System.out.println("O número da sua conta é: "+ contaPoupanca.getNumero());

					break;
				case 3:
					System.out.print("Número da conta: ");
					int numeroContaDeposito = entrada.nextInt();
					System.out.print("Valor do depósito: ");
					double valorDeposito = entrada.nextDouble();
					Conta contaDeposito = banco.buscarConta(numeroContaDeposito);
					if (contaDeposito != null) {
						contaDeposito.depositar(valorDeposito);
						System.out.println("Depósito realizado com sucesso!");
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;
				case 4:
					System.out.print("Número da conta: ");
					int numeroContaSaque = entrada.nextInt();
					System.out.print("Valor do saque: ");
					double valorSaque = entrada.nextDouble();
					Conta contaSaque = banco.buscarConta(numeroContaSaque);
					if (contaSaque != null) {
						contaSaque.sacar(valorSaque);
						System.out.println("Saque realizado com sucesso!");
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;
				case 5:
					System.out.print("Número da conta de origem: ");
					int numeroContaOrigem = entrada.nextInt();
					System.out.print("Número da conta de destino: ");
					int numeroContaDestino = entrada.nextInt();
					System.out.print("Valor da transferência: ");
					double valorTransferencia = entrada.nextDouble();
					Conta contaOrigem = banco.buscarConta(numeroContaOrigem);
					Conta contaDestino = banco.buscarConta(numeroContaDestino);
					if (contaOrigem != null && contaDestino != null) {
						contaOrigem.transferir(valorTransferencia, contaDestino);
						System.out.println("Transferência realizada com sucesso!");
					} else {
						System.out.println("Conta de origem ou destino não encontrada.");
					}
					break;
				case 6:
					System.out.print("Número da conta: ");
					int numeroContaExtrato = entrada.nextInt();
					Conta contaExtrato = banco.buscarConta(numeroContaExtrato);
					if (contaExtrato != null) {
						contaExtrato.imprimirExtrato();
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;
				case 7:
					executando = false;
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
		}
		entrada.close();
	}
}
