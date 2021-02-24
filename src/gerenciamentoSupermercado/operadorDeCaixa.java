/*
	==> Sales control system // Sistema de controle de vendas <== 
	Autor: Thiago Hercules de Aguiar Silva. // Software engineer junior
	
	==> Functions in the application: 
		--> Login with: 
			.Manager
			.Cashier
			.Client
			 
		--> Control Options: // Manager 
			.Add and remove products
			.Issue inventory report
			.Issue cashier sales report
			
		--> Control acess to cashier 
		
		--> Options: // Clients
			.Access product inventory
			.Customers can choose products
			.Purchase
		
		--> More fuctions: 
			.Register and remove products
			.Issue reports
			.Access boxes
			.Place products in the shopping cart
			.Make purchases
			
CONTACTS AND INFOS:
|--> e-mail: thhercules2012@gmail.com
|--> LinkedIn: https://www.linkedin.com/in/thiago-hercules-2669901ba/
|--> GitHub: https://github.com/Thercules
|--> Personal Instagram: https://www.instagram.com/t.hercules02/
|--> Professional Instagram: https://www.instagram.com/tdesigner._/
|--> Behance Portfolio: https://www.behance.net/thercules/appreciated

=====================			=====================			=====================
Copy is not permited.			Copy is not permited.			Copy is not permited.
=====================			=====================			=====================
*/
package gerenciamentoSupermercado;

public class operadorDeCaixa extends funcionario  {
	   
    public operadorDeCaixa(String nome, String userName, String senha) {
        super(nome, userName, senha);
    }

    @Override
    public void adicionarProduto(Produto produto, double quantidade) {
        System.out.println("********************************************************************");
        System.out.println("*                ATENÇÃO - OPERAÇÃO NÃO PERMITIDA                  *");
        System.out.println("*   Operadores de caixa NÂO podem adicionar produtos ao estoque    *");
        System.out.println("********************************************************************");
    }

    // Remover produto deve ser chamado ao efetuar venda
    @Override
    public void removerProduto(String codigo, double quantidade) {
        estoqueDeProdutos.removerProduto(codigo, quantidade);
    }

    @Override
    public void mostrarEstoque() {
        System.out.println("********************************************************************");
        System.out.println("*                ATENÇÃO - OPERAÇÃO NÃO PERMITIDA                  *");
        System.out.println("*  Operadores de caixa NÂO possuem acesso para mostrar o estoque   *");
        System.out.println("********************************************************************");
    }
    
}
