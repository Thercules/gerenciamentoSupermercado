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

import java.util.ArrayList;
import java.util.Iterator;

public class caixa {
    private ArrayList<venda> vendas;
    private funcionario operadorCaixa;
    private int numeroDoCaixa;
    private balanca balanca;
    
    public caixa(int numero, operadorDeCaixa operador){
        this.numeroDoCaixa = numero;
        this.operadorCaixa = operador;
        this.balanca = new balanca();
        vendas = new ArrayList<>();
    }    

    public caixa(int numero){
        this.numeroDoCaixa = numero;
        this.balanca = new balanca();
        vendas = new ArrayList<>();
    } 
    
    public void relatorioCaixa(){
        if (vendas.size() > 0) {
            Iterator it = vendas.iterator();
            while(it.hasNext()){
                venda venda = (venda) it.next();
                utilitario.ImprimaMensagem(venda.dadosVenda());
            }
        }else{
            utilitario.ImprimaMensagem("*           Não há vendas registradas nesse caixa              *");
        }
    }
    
    public ArrayList<venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<venda> vendas) {
        this.vendas = vendas;
    }
    
    public int getNumeroDoCaixa() {
        return numeroDoCaixa;
    }

    public void setNumeroDoCaixa(int numeroDoCaixa) {
        this.numeroDoCaixa = numeroDoCaixa;
    }

    public funcionario getOperadorCaixa() {
        return operadorCaixa;
    }

    public void setOperadorCaixa(funcionario  operadorCaixa) {
        this.operadorCaixa = operadorCaixa;
    }
    
    public void cancelarVenda(){
        
    }

    private double calcularValorPorItem(double valorDaUnidadeProduto, int quantidade){
        return balanca.calcularValorPorItem(valorDaUnidadeProduto, quantidade);
    }
    
    private double calcularValorPorPeso(double valorDoPeso, double quantidade){
        return balanca.calcularValorPorPeso(valorDoPeso, quantidade);
    }
    
    @Override
    public String toString() {
         return "Caixa " + numeroDoCaixa;        
    }
    
    public venda iniciarVenda(cliente cliente){
        venda venda = new venda(this, cliente);
        venda.vender();
        boolean pagamento = venda.formaDePagamento();
        if(pagamento){
             vendas.add(venda);
        }else{
            utilitario.ImprimaMensagem("*  !!!! FALHA AO COMPRAR !!!!  *");
            return null;
        }
       
        return venda;
    }
}
