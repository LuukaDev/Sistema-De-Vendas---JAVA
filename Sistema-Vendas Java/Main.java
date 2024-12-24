import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Produtos> produtosList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================================");
            System.out.println("       SISTEMA DE VENDAS");
            System.out.println("==================================");
            System.out.println("1 - Cadastro de Produtos");
            System.out.println("2 - Vender Produto");
            System.out.println("3 - Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            String opcaoEscolha = sc.nextLine();

            if (opcaoEscolha.equals("1")) {
                // Cadastro de produtos
                System.out.println("\n==================================");
                System.out.println("    CADASTRO DE NOVO PRODUTO");
                System.out.println("==================================");
                System.out.print("Informe o nome do produto: ");
                String nome = sc.nextLine();
                System.out.print("Informe o preço do produto: ");
                double preco = Double.parseDouble(sc.nextLine());
                System.out.print("Informe a quantidade do produto: ");
                int quantidade = Integer.parseInt(sc.nextLine());

                Produtos novoProduto = new Produtos(nome, preco, quantidade);
                produtosList.add(novoProduto);

                System.out.println("\nProduto cadastrado com sucesso!");
                System.out.println("==================================");

            } else if (opcaoEscolha.equals("2")) {
                // Venda de produtos
                double precoTotal = 0.0;

                System.out.println("\n==================================");
                System.out.println("          VENDA DE PRODUTOS");
                System.out.println("==================================");

                while (true) {
                    // Exibir lista de produtos disponíveis
                    System.out.println("Produtos disponíveis:");
                    for (int i = 0; i < produtosList.size(); i++) {
                        Produtos produto = produtosList.get(i);
                        System.out.println(i + " - " + produto.Mostrar());
                    }
                    System.out.println("----------------------------------");

                    System.out.print("Informe o código do produto: ");
                    int idProduto = Integer.parseInt(sc.nextLine());

                    if (idProduto < 0 || idProduto >= produtosList.size()) {
                        System.out.println("Código de produto inválido!");
                        continue;
                    }

                    Produtos produtoVendido = produtosList.get(idProduto);

                    System.out.print("Informe a quantidade deste produto: ");
                    int quantidadeVendida = Integer.parseInt(sc.nextLine());

                    if (quantidadeVendida > produtoVendido.getquantidade()) {
                        System.out.println("Quantidade insuficiente em estoque!");
                        continue;
                    }

                    produtoVendido.retirar(quantidadeVendida);
                    precoTotal += produtoVendido.getpreco() * quantidadeVendida;

                    System.out.print("Deseja adicionar mais itens ao carrinho? (sim/nao): ");
                    String carrinho = sc.nextLine();

                    if (!carrinho.equalsIgnoreCase("sim")) {
                        break;
                    }
                }

                System.out.println("\n==================================");
                System.out.printf("Total da compra: R$ %.2f%n", precoTotal);
                System.out.println("==================================");

            } else if (opcaoEscolha.equals("3")) {
                // Sair do sistema
                System.out.println("\n==================================");
                System.out.println("   Obrigado por usar o sistema!");
                System.out.println("==================================");
                break;

            } else {
                System.out.println("\n==================================");
                System.out.println("       Opção inválida!");
                System.out.println("==================================");
            }
        }

        sc.close();
    }
}

class Produtos {
    private int id;
    private String nomeProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Produtos(String nomeProduto, double precoProduto, int quantidadeProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public double somaProdutos() {
        return this.precoProduto * this.quantidadeProduto;
    }

    public void retirar(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidadeProduto) {
            throw new IllegalArgumentException("Quantidade insuficiente no estoque.");
        }
        this.quantidadeProduto -= quantidadeVendida;
    }

    public void adicionar(int quantidadeProduto) {
        this.quantidadeProduto += quantidadeProduto;
    }

    public String Mostrar() {
        return String.format("Nome: %s | Preço: R$ %.2f | Quantidade: %d", this.nomeProduto, this.precoProduto, this.quantidadeProduto);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nomeProduto;
    }

    public void setNome(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getpreco() {
        return this.precoProduto;
    }

    public void setPreco(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getquantidade() {
        return this.quantidadeProduto;
    }

    public void setQuantidade(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}
