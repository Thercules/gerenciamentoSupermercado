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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class gerente extends funcionario {
    
    public gerente(String nome, String userName, String senha) {
        super(nome, userName, senha);
        estoqueDeProdutos.copiarEstoque();
    }

    public void emitirRelatorioDeEstoque() {
        Scanner scanner = new Scanner(System.in);
        utilitario.ImprimaMensagem("*                     RELATÓRIO DE ESTOQUE                      *", 
                "*                    Estoque no INÍCIO do dia                   *");
        estoqueDeProdutos.exibirCopiaInicialDoEstoque();

        utilitario.ImprimaMensagem("*                  Estoque no FINAL do dia                      *");
        estoqueDeProdutos.mostrarEstoque(1);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Aperte ENTER para continuar ...");
        scanner.nextLine();
    }
    
    public void emitirRelatorioDeVendas(List<caixa> caixas){
        Iterator i = caixas.iterator();
        // acessa caixa por caixa
        while (i.hasNext()) { 
            caixa caixa = (caixa)i.next();
            utilitario.ImprimaMensagem(
                  "*                   Relatório do Caixa: "+ caixa.getNumeroDoCaixa() +"                  *");
            caixa.relatorioCaixa();
            utilitario.Continuar();
        }
    }
}