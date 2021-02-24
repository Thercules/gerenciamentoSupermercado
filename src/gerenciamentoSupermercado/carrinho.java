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

public class carrinho{
    private Map<String, List<Produto>> produtosCarrinho;
    
    private double valorCompra;
    
    public carrinho(){
        produtosCarrinho = new LinkedHashMap<String, List<Produto>>();
        valorCompra = 0;
    }
    
    public Map getProdutosCarrinho(){
        return this.produtosCarrinho;
    }
    
    public double getValorCompra(){
        return this.valorCompra;
    }
    
    public void setValorCompra(double valor){
        this.valorCompra = valor;
    }
    
    public void addProduto(Produto produto, double quantidade){
        List<Produto> produtosDoCodigo;
        String codigo = produto.getCodigo();
        
        if(produtosCarrinho.containsKey(codigo)){
            produtosDoCodigo = produtosCarrinho.get(codigo);
            
            if (produtosDoCodigo.get(0).getNome().equals(produto.getNome())){
                if (produto instanceof ProdutoUnitario) {
                    while (quantidade > 0) {                
                        produtosDoCodigo.add(produto);
                        quantidade--;
                    }
                }
                else if (produto instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)produtosDoCodigo.get(0);
                    pdt.setQtdQuilos(pdt.getQtdQuilos() +  quantidade);
                    produtosDoCodigo = new LinkedList<Produto>();
                    produtosDoCodigo.add(pdt);
                }

                produtosCarrinho.put(codigo, produtosDoCodigo);
            }
            else{
                System.out.println("ATENÇÃO\tATENÇÃO\tATENÇÃO\tATENÇÃO\tATENÇÃO");
                System.out.println("Produto NÃO foi adicionado pois o codigo '" + produto.getCodigo() + "' possui apenas produtos '"
                +produtosDoCodigo.get(0).getNome() + "' e voce esta tentando adicionar '" + produto.getNome() + "'");
            }
        }else{
            produtosDoCodigo = new LinkedList<Produto>();
            
            if (produto instanceof ProdutoUnitario) {
                while (quantidade > 0) {                
                    produtosDoCodigo.add(produto);
                    quantidade--;
                }
            } 
            else if (produto instanceof ProdutoQuilo) {
                ((ProdutoQuilo) produto).setQtdQuilos(quantidade);
                produtosDoCodigo.add(produto);
            }
            
            produtosCarrinho.put(codigo, produtosDoCodigo);
        }
    }
    
    public void devolverProdutosCarrinho(){
        if(this.produtosCarrinho.size() > 0){
            String codigo;
            int quantidade;
           Iterator it = produtosCarrinho.keySet().iterator();
           while(it.hasNext()){
               codigo = (String) it.next();
               List produtos = produtosCarrinho.get(codigo);
               quantidade = produtos.size();
               Produto produto_devolucao = (Produto)produtos.get(0);

                if(produto_devolucao instanceof ProdutoUnitario){
                    estoqueDeProdutos.adicionarProduto(produto_devolucao, quantidade);
                }
                if(produto_devolucao instanceof ProdutoQuilo){
                    ProdutoQuilo prodKg = estoqueDeProdutos.ObtenhaProdutoQuiloTemporario(produto_devolucao);
                    double kilos = prodKg.getQtdQuilos();
                    estoqueDeProdutos.adicionarProduto(prodKg, kilos);
                }
           }
           this.produtosCarrinho.clear();
            utilitario.ImprimaMensagem("*   COMPRA CANCELADA, VOCÊ NÃO POSSUI MAIS PRODUTOS NO SEU CARRINHO!   *");
            exibirCarrinhoCliente();
        }
    }
    
    public void exibirCarrinhoCliente(){
        Produto p = null;
        
        Iterator it = produtosCarrinho.keySet().iterator();
        if(produtosCarrinho.size() > 0){
            System.out.println("***** PRODUTOS NO CARRINHO *****");
            int quantidade = 0;
            double quilos = 0;
            while (it.hasNext()) {
                String codigo = (String)it.next();
                Iterator produtos = produtosCarrinho.get(codigo).iterator();
                boolean mostrarNomeProduto = true;
                List prodUnidade = (List) produtosCarrinho.get(codigo);
                while (produtos.hasNext()) {
                    p = (Produto)produtos.next();
                    quantidade++;
                    if (mostrarNomeProduto) {
                        System.out.println("Código: " + codigo);
                        System.out.println("Produto: " + p.getNome());
                        mostrarNomeProduto = false;
                    }
                }
                if (p instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)p;
                    System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                }
                if (p instanceof ProdutoUnitario) {
                    System.out.println("Quantidade no carrinho = " + quantidade + "\n");
                }
                quantidade = 0;
            }
        }else{
            System.out.println("*             Você não possui produtos no carrinho              *");
        }
        utilitario.Continuar();
    }
    
    //Verifica se o carrinho de compras está vazio
    public boolean verificaCarrinho(){
        return this.produtosCarrinho.size() > 0 ? true : false;
    }
    
     public double calcularPrecoCarrinho(){
        
        // Calcular o valor total da compra usando o somatorio de:
        // - calcularValorPorItem
        // - calcularValorPorPeso
        // Calcular troco do cliente se pagar em $
       double valorTotal = 0;
       Iterator itMap = produtosCarrinho.keySet().iterator();
       List<Produto> list;
       int quantidade = 0;
       double valorPeso = 0;
       double ktdKilo = 0;
       while(itMap.hasNext()){
           String codigo = (String) itMap.next();
           Iterator produtos = this.produtosCarrinho.get(codigo).iterator();
           list = (List) produtosCarrinho.get(codigo);
           
           while(produtos.hasNext()){
               Produto produtoList = (Produto) produtos.next();

               		// --> Função: Fazer a variavel receber o valor do calculo por kilo.
                if(produtoList instanceof ProdutoQuilo){
                    // --> Função: Pega a quantidade de kilos e o valor do peso para que a balança possa calcular
                    ProdutoQuilo produtokg = (ProdutoQuilo) list.get(0);
                    valorPeso = produtokg.getValor();
                    ktdKilo = produtokg.getQtdQuilos();
                    valorTotal += balanca.calcularValorPorPeso(valorPeso,ktdKilo);
                }
                //Fazer a variavel "valorTotal" receber o valor do calculo por Unidade
                else if(produtoList instanceof ProdutoUnitario){
                    //QUAL A MELHOR FORMA DE CHAMAR O MÉTODO calcularValorPorUnidade chamar da balança ou do caixa???
                     quantidade = list.size();
                     ProdutoUnitario produtounit = (ProdutoUnitario) list.get(0);
                     valorTotal += balanca.calcularValorPorItem(produtounit.getValor(), quantidade);
                } 
           }
       }
       return valorTotal;
    }
    
    public double calcularValorCompra(){  
        return this.getValorCompra();
    }
    
}