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

public class leitor {
    
    public static void mostrarValorProduto(String codigo){
        utilitario.ImprimaMensagem("*               Procurando produto de c�gido '" + codigo + "'           *");    
        
        Produto produto = estoqueDeProdutos.seekProduto(codigo);
        if(produto != null) {
            System.out.println("*   Produto: "+ produto.getNome() + "   *\n*   Pre�o: R$ " + produto.getValor() + "   *");
        }
        else{
            utilitario.ImprimaMensagem("*                    Produto n�o encontrado!                    *");
        }
        System.out.println();
        System.out.println();
        System.out.println("*****************************************************");
    }
    
}
