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
import java.util.stream.Collectors;

public class estoqueDeProdutos {
    // key = codigo do produto , value = lista com a quantidade do mesmo produto em estoque
    public static Map<String, List<Produto>> estoque = new LinkedHashMap<String, List<Produto>>();;
    private static Map<String, List<Produto>> copiaDoEstoque;
    private static Produto p;

    private static Map<String, List<Produto>> getCopiaDoEstoque() {
        return copiaDoEstoque;
    }

    private static void setCopiaDoEstoque(Map<String, List<Produto>> copiaDoEstoque) {
        copiaDoEstoque = copiaDoEstoque;
    }
    
    public static void adicionarProduto(Produto produto, double quantidade){
        List<Produto> produtosDoCodigo;
        String codigo = produto.getCodigo();
        
        if(estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            
            if (produtosDoCodigo.get(0).getNome().equals(produto.getNome())) {
                if (produto instanceof ProdutoUnitario) {
                    while (quantidade > 0) {                
                        produtosDoCodigo.add(produto);
                        quantidade--;
                    }
                }
                else if (produto instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = ObtenhaProdutoQuiloTemporario(produto);
                    pdt.setQtdQuilos(((ProdutoQuilo)produtosDoCodigo.get(0)).getQtdQuilos() +  quantidade);
                    produtosDoCodigo = new LinkedList<Produto>();
                    produtosDoCodigo.add(pdt);
                }

                estoque.put(codigo, produtosDoCodigo);
            }
            else{
                System.out.println("ATEN��O\tATEN��O\tATEN��O\tATEN��O\tATEN��O");
                System.out.println("Produto N�O foi adicionado pois o codigo '" + produto.getCodigo() + "' possui apenas produtos '"
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
                produtosDoCodigo.add(produto);
            }
            
            estoque.put(codigo, produtosDoCodigo);
        }
    }
    
    public static void removerProduto(String codigo, double quantidade){
        List<Produto> produtosDoCodigo;
        boolean removerDoEstoque = false;
        
        if(estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            p = produtosDoCodigo.get(0);
            if (produtosDoCodigo.get(0) instanceof ProdutoUnitario) {
                double temp = quantidade;
                for (int i = 0; i < quantidade; i++) {
                    if(produtosDoCodigo.size() > 0 && temp > 0) {                
                        produtosDoCodigo.remove(produtosDoCodigo.get(0));
                        temp--;
                    }
                    else {                        
                        removerDoEstoque = true;
                        break;
                    }
                }
            }
            else if (p instanceof ProdutoQuilo){
                ProdutoQuilo pdtQuilo = ObtenhaProdutoQuiloTemporario(p);
                        
                double peso = pdtQuilo.getQtdQuilos() - quantidade;
                removerDoEstoque = peso <= 0 ? true : false;
                pdtQuilo.setQtdQuilos(peso);
                produtosDoCodigo.clear();
                produtosDoCodigo.add(pdtQuilo);
            }
            if (removerDoEstoque) {
                utilitario.ImprimaMensagem("*  ATEN��O! O estoque desse produto acabou: " + p.getNome() +"  *"); 
                estoque.remove(codigo);
            }
            else{
                estoque.put(codigo, produtosDoCodigo);
            }
        }else{
            utilitario.ImprimaMensagem("*  ATEN��O! Produto com o c�digo " + codigo + " n�o encontrado  *");
        }
        System.out.println();
    }
    
    public static void mostrarEstoque(int opcaoDeEstoque){
        Map<String, List<Produto>> estoqueTemp = null;
        if (opcaoDeEstoque == 1) {
            estoqueTemp = estoque;
        }
        else if (opcaoDeEstoque == 2) {
            estoqueTemp = getCopiaDoEstoque();
        }
        utilitario.ImprimaMensagem("*                    ESTOQUE DE PRODUTOS                        *");
        Iterator listasDeCodigos = estoqueTemp.keySet().iterator();
        int quantidade = 0;
        double quilos = 0;
        while (listasDeCodigos.hasNext()) {
            String codigo = (String)listasDeCodigos.next();
            if (estoqueTemp.get(codigo).size() > 0) { // listar apenas se existir produtos
                Iterator produtos = estoqueTemp.get(codigo).iterator();
                boolean mostrarNomeProduto = true;

                while (produtos.hasNext()) {
                    p = (Produto)produtos.next();

                    if (mostrarNomeProduto) {
                        System.out.println("C�digo: " + codigo);
                        System.out.println("Produto: " + p.getNome());
                        mostrarNomeProduto = false;
                    }
                    if (p instanceof ProdutoQuilo) {
                        ProdutoQuilo pdt = (ProdutoQuilo)p;
                        System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                    }

                    quantidade++;
                }

                if (p instanceof ProdutoUnitario) {
                    System.out.println("Quantidade em estoque = " + quantidade + "\n");
                }
                quantidade = 0;
            }
        }
        System.out.println();
    }
 
    public static double precoPorCodigo(String codigo){
        if(estoque.containsKey(codigo)){
            Iterator it = estoqueDeProdutos.estoque.get(codigo).iterator();
            Produto produto = null;
            if(it.hasNext()){
                produto = (Produto) it.next();
                return produto.calcularValor(1);
            }else{
                return 0.0;
            }
        }
        return 0.0;
    }
    
    // --> Fun��o: Verifica se existe o produto, caso n�o exista, retorna null.
    public static Produto seekProduto(String codigo) {
        Map<String, List<Produto>> temp = estoque.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> new LinkedList(e.getValue())));
        if(temp.containsKey(codigo)){
            Iterator it = temp.get(codigo).iterator();
            
            if(it.hasNext()){
                Produto pdt = (Produto) it.next();
                if (pdt instanceof ProdutoUnitario) {
                    String codigo_un = pdt.getCodigo();
                    String nome_un = pdt.getNome();
                    double valor_un = pdt.getValor();
                    ProdutoUnitario pdt_un = new ProdutoUnitario(codigo_un, nome_un, valor_un);
                    return pdt_un;
                }
                else if (pdt instanceof ProdutoQuilo){
                    return ObtenhaProdutoQuiloTemporario(pdt);
                }
            }
        }else{
                utilitario.ImprimaMensagem("*                   Produto indispon�vel!                     *");
        }
        return null;
    }
    
    //m�todo que verifica se tem o produto no estoque ou se existe a quanitdade desejada do mesmo, caso alguma dessas afirma��es seja falsa, ele retorna null
    public static boolean produtoParaCompra(String codigo, double quantidade, Boolean validarQuantidade){
        //Verifica se existe o produto no estoque e se possui a quantidade esperada
        boolean retorno = false;
        List<Produto> produtosDoCodigo = null;
        if(estoqueDeProdutos.estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            if(produtosDoCodigo.get(0) instanceof ProdutoUnitario){
                if(produtosDoCodigo.size() >= quantidade){
                    retorno = true;
                }else{
                    if (validarQuantidade) {
                        utilitario.ImprimaMensagem("*                    Infelizmente s� possuimos "+produtosDoCodigo.size()+" unidades                      *");
                        retorno = false;
                    }
                    else{
                        retorno = true;
                    }
                }
            }
            if(produtosDoCodigo.get(0) instanceof ProdutoQuilo){
                ProdutoQuilo prodKg = (ProdutoQuilo) produtosDoCodigo.get(0);
               
                if(prodKg.getQtdQuilos() >= quantidade){
                    retorno = true;
                }else{
                    if (validarQuantidade) {
                        utilitario.ImprimaMensagem("*                    Infelizmente s� temos "+prodKg.getQtdQuilos()+" KG deste produto!                      *");
                        retorno = false;
                    }
                    else{
                        retorno = true;
                    }
                }
            } 
        }else{
            utilitario.ImprimaMensagem("*                    Produto Indispon�vel!                      *");
            retorno = false;
        }
        return retorno;
    }
    
     public static void exibirEstoqueCliente(){
        Produto p = null;
        utilitario.ImprimaMensagem("*                     PRODUTOS DISPON�VEIS                      *");
        Iterator listasDeCodigos = estoqueDeProdutos.estoque.keySet().iterator();
        int quantidade = 0;
        double quilos = 0;
        
        while (listasDeCodigos.hasNext()) {
            String codigo = (String)listasDeCodigos.next();
            Iterator produtos = estoque.get(codigo).iterator();
            boolean mostrarNomeProduto = true;
           
            while (produtos.hasNext()) {
                p = (Produto)produtos.next();
                
                if (mostrarNomeProduto) {
                    System.out.println("C�digo: " + codigo);
                    System.out.println("Produto: " + p.getNome());
                    mostrarNomeProduto = false;
                }
                
                if (p instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)p;
                    System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                }

                quantidade++;
            }
            
            if (p instanceof ProdutoUnitario) {
                System.out.println("Quantidade em estoque = " +quantidade + "\n");
            }
            
            quantidade = 0;
        }
        
        System.out.println();
    }
     
    public static ProdutoQuilo ObtenhaProdutoQuiloTemporario(Produto temp) {
        String codigo_quilo = temp.getCodigo();
        String nome_quilo = temp.getNome();
        double valor_quilo = temp.getValor();
        double qtd_quilo = ((ProdutoQuilo) temp).getQtdQuilos();
        ProdutoQuilo pdtQuilo = new ProdutoQuilo(codigo_quilo, nome_quilo,
                valor_quilo, qtd_quilo);
        return pdtQuilo;
    }
     
     // M�todo respons�vel por criar o estoque inicial de produtos. Alimentar o sistema.
    public static void Feed(){
           System.out.println("*****************************************************************");
           System.out.println("*              Carga inicial do estoque de produtos             *");
           System.out.println("*                            Aguarde...                         *");
        ProdutoUnitario leite = new ProdutoUnitario("10", "Leite", 2.50);
        adicionarProduto(leite, 50.0);
        
        ProdutoUnitario sal = new ProdutoUnitario("20", "Pacote de sal", 0.95);
        adicionarProduto(sal, 30.0);
        
        ProdutoUnitario acucar = new ProdutoUnitario("30", "Pacote de a��car", 1.50);
        adicionarProduto(acucar, 30.0);
        
        ProdutoUnitario refrigerante = new ProdutoUnitario("40", "Refrigerante 2 lt", 5.50);
        adicionarProduto(refrigerante, 100.0);
        
        ProdutoUnitario cerveja = new ProdutoUnitario("50", "Cerveja 600 ml", 8.50);
        adicionarProduto(cerveja, 100.0);
        
        ProdutoUnitario arroz = new ProdutoUnitario("60", "Arroz", 11.95);
        adicionarProduto(arroz, 150.0);
        
        ProdutoUnitario sabao = new ProdutoUnitario("70", "Caixa de sab�o em p�", 8.65);
        adicionarProduto(sabao, 75);
        
        ProdutoUnitario macarrao = new ProdutoUnitario("80", "Pacote de macarr�o", 1.39);
        adicionarProduto(macarrao, 80.0);
        
        ProdutoUnitario biscoito = new ProdutoUnitario("90", "Saco de biscoito", 4.99);
        adicionarProduto(biscoito, 35.0);
        
        ProdutoUnitario cafe = new ProdutoUnitario("100", "Caf�", 4.99);
        adicionarProduto(cafe, 60.0);
        
        ProdutoUnitario oleo = new ProdutoUnitario("110", "�leo de soja", 3.20);
        adicionarProduto(oleo, 64.0);
        
        ProdutoUnitario feijao = new ProdutoUnitario("120", "Feij�o", 4.99);
        adicionarProduto(feijao, 80.0);
        
        ProdutoUnitario esponja = new ProdutoUnitario("130", "Esponja met�lica", 3.50);
        adicionarProduto(esponja, 50.0);
        
        ProdutoUnitario detergente = new ProdutoUnitario("140", "Detergente", 0.99);
        adicionarProduto(detergente, 75.0);
        
        ProdutoUnitario farinha = new ProdutoUnitario("150", "Saco de farinha", 2.99);
        adicionarProduto(farinha, 45.0);
        
        ProdutoUnitario manteiga = new ProdutoUnitario("160", "Manteiga", 5.50);
        adicionarProduto(manteiga, 65.0);
        
        ProdutoUnitario sabonete = new ProdutoUnitario("170", "Sabonete", 0.65);
        adicionarProduto(sabonete, 150.0);
        
        ProdutoQuilo tomate = new ProdutoQuilo("11", "Tomate", 3.50, 100);
        adicionarProduto(tomate, 0);
        
        ProdutoQuilo batata = new ProdutoQuilo("21", "Batata", 4.99, 89);
        adicionarProduto(batata, 0);
        
        ProdutoQuilo carne = new ProdutoQuilo("31", "Carne de boi", 17.50, 350);
        adicionarProduto(carne, 0);
        
        ProdutoQuilo limao = new ProdutoQuilo("41", "Lim�o", 1.99, 75);
        adicionarProduto(limao, 0);
        
        ProdutoQuilo mandioca = new ProdutoQuilo("51", "Mandioca", 2.45, 134);
        adicionarProduto(mandioca, 0);
        
        ProdutoQuilo maca = new ProdutoQuilo("61", "Ma��", 3.50, 65);
        adicionarProduto(maca, 0);
        
        System.out.println("*                  Estoque criado com sucesso!                  *");
        System.out.println("*****************************************************************");
    }
    
    // M�todo respons�vel por criar uma c�pia do estoque de produtos para que o gerente 
    // possa emitir o relat�rio de estoque inicial x estoque final
    public static void copiarEstoque(){
        copiaDoEstoque = estoque.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> new LinkedList(e.getValue())));
    }
    
    // M�todo respons�vel por chamar a exibi��o do estoque inicial.
    public static void exibirCopiaInicialDoEstoque(){
        mostrarEstoque(2);
    }
}