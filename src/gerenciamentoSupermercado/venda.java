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

import java.util.Scanner;

public class venda {
    private cliente cliente;
    private caixa caixa;
    private tipoDPagamento formaDePAgamento;
    private double valorVenda, troco;
    
    public venda(caixa caixa, cliente cliente){
        this.cliente = cliente;
        this.caixa = caixa;
    }
    
    public tipoDPagamento getFormaDePagamento() {
        return formaDePAgamento;
    }

    public void setFormaDePagamento(tipoDPagamento formaDePAgamento) {
        this.formaDePAgamento = formaDePAgamento;
    }
    
    public caixa getCaixa(){
        return this.caixa;
    }
    
    public double getValorVenda(){
        return this.valorVenda;
    }
    
    public void vender(){
        this.valorVenda = cliente.getCarrinho().calcularPrecoCarrinho();        
    }
    
    public String dadosVenda(){
        String dados =  "\n"
                        + "*    Operador do Caixa: "+ caixa.getOperadorCaixa().getNome()+" *\n"
                        + "*    Forma de Pagamento: "+ this.formaDePAgamento+"\n"
                        + "*    Valor da venda: "+ String.format( "%.2f", this.valorVenda)+" \n"
                        + "*    Troco: "+ String.format( "%.2f", this.troco);
        return dados;
    }
    
    //Se ocorrer tudo OK com a forma de pagamento, retorna true
    public boolean formaDePagamento(){
        Scanner scan = new Scanner(System.in);
        double dinheiro;
        vender();
        utilitario.ImprimaMensagem("*           O VALOR DA SUA COMPRA É: R$ "+ String.format( "%.2f", getValorVenda()) +"            *");
        System.out.println("***********************************************************");
        System.out.println("*   ESCOLHA SUA FORMA DE PAGAMENTO!  \n ( 1 ) DINHEIRO  \n ( 2 ) CARTÃO                                           *");
        System.out.println("***********************************************************");
        int formaPagamento = scan.nextInt();
       
        if((int)tipoDPagamento.CARTAO.ordinal() == formaPagamento-1){
            System.out.println("Compra realizada com sucesso!");
            this.formaDePAgamento = getFormaDePagamento().CARTAO;
            return true;
        }
        else if((int)tipoDPagamento.DINHEIRO.ordinal() == formaPagamento-1){
            do{
                System.out.println("Entre com o valor em dinheiro: ");
                dinheiro = scan.nextDouble();

                if(dinheiro > getValorVenda()){
                    this.troco = dinheiro - getValorVenda();
                    utilitario.ImprimaMensagem("Troco de: R$"+  String.format( "%.2f", this.troco) );
                    this.formaDePAgamento = getFormaDePagamento().DINHEIRO;
                    return true;
                }else{
                    System.out.println("Valor insuficiente!!!");
                }
            }while(dinheiro < getValorVenda());
        }
       return false;  
    }
}
