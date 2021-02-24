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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class supermercado{

    private static Scanner scanner = new Scanner(System.in);
    private static List<funcionario> funcionarios = new ArrayList<funcionario>();
    private static List<caixa> caixas = new ArrayList<caixa>();
    private static List<String> senhas = new ArrayList<String>()
    {{ add("1111"); add("1122"); add("1133"); add("1144"); add("1155"); }};
    
    public static void main(String[] args) {
        Saudacao();
        Feed();
        CriarFuncionarios();
        
        gerente gerente = (gerente)funcionarios.get(0);
        
        Boolean sairMenu = false;
        do{
            int chances = 3;
            int tentativas = 0;
            utilitario.ImprimaMensagem("*                            ACESSO                             *");
            System.out.println(" ( 1 ) Gerente \n ( 2 ) Funcionário \n ( 3 ) Cliente \n ( 0 ) Sair do sistema");
            int opcao = scanner.nextInt();
        
            switch(opcao){
                case 1: /* Gerente, declarando nº de tentativas para login */ 
                    ControleMenuGerente(gerente, tentativas, chances);
                    break;
                case 2: /* Declarando funcionário */
                    Boolean sairMenuOperador = false;
                    do{
                        MostrarMenuListaDeCaixas(caixas);
                        int opCaixa = scanner.nextInt();
                        
                        switch(opCaixa){
                            case 1:
                                caixa c1 = caixas.get(0);
                                if (c1.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                    
                                    do{
                                        System.out.println(" -- > Operador, digite sua senha de login.");
                                        String senhaCaixa = scanner.next();
                                       
                                        if (senhas.contains(senhaCaixa)){
                                            funcionario f = (funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                utilitario.ImprimaMensagem("*               Bem vindo ao Caixa 1, " + f.getNome() + "               *");
                                                utilitario.Continuar();
                                                c1.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("****    Atenção!    ****\nO Operador(a) " + f.getNome() + " está logado em outro caixa.");
                                            }
                                        }else{
                                            System.out.println("****    Atenção!    ****\nSenha incorreta.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                    
                                }else{
                                    utilitario.ImprimaMensagem("*                == LOGOUT EFETUADO NO CAIXA 1 ==               *",
                                                               "*                    == CAIXA 1 ESTÁ LIVRE ==                   *");
                                    c1.setOperadorCaixa(null);
                                }
                                
                                break;
                            case 2:
                                caixa c2 = caixas.get(1);
                                
                                if (c2.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                   
                                    do{
                                        System.out.println("Operador, digite sua senha");
                                        String senhaCaixa = scanner.next();
                                        
                                        if (senhas.contains(senhaCaixa)){
                                            funcionario f = (funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                utilitario.ImprimaMensagem("*               Bem vindo ao Caixa 2, " + f.getNome() + "               *");
                                                utilitario.Continuar();
                                                c2.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("****    Atenção!    ****\nOperador já " + f.getNome() + " está logado em outro caixa.");
                                            }
                                        }else{
                                            System.out.println("****    Atenção!    ****\nSenha incorreta.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                    
                                }else{
                                	 utilitario.ImprimaMensagem("*                == LOGOUT EFETUADO NO CAIXA 2 ==               *",
                                			 					"*                    == CAIXA 2 ESTÁ LIVRE ==                   *");
                                	 		c2.setOperadorCaixa(null);
                                	}
                                
                                break;
                            case 3:
                                caixa c3 = caixas.get(2);
                                
                                if (c3.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                    
                                    do{
                                        System.out.println("Operador, digite sua senha");
                                        String senhaCaixa = scanner.next();
                                        
                                        if (senhas.contains(senhaCaixa)){
                                            funcionario f = (funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                utilitario.ImprimaMensagem("*               Bem vindo ao Caixa 3, " + f.getNome() + "               *");
                                                utilitario.Continuar();
                                                c3.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("****    Atenção!    ****\nOperador já " + f.getNome() + " está logado em outro caixa.");
                                            }
                                        }else{
                                            System.out.println("****    Atenção!    ****\nSenha incorreta.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                }else{
                                	utilitario.ImprimaMensagem("*                == LOGOUT EFETUADO NO CAIXA 3 ==               *",
            			 										"*                    == CAIXA 3 ESTÁ LIVRE ==                   *");
                                	c3.setOperadorCaixa(null);
                                }
                                break;
                            case 0:
                                sairMenuOperador = true;
                                break;
                            default:
                                break;
                        }        
                    }while(!sairMenuOperador);
                    
                    scanner.nextLine();
                    break;
                case 3: // cliente
                    if (ObtenhaCaixasDisponiveis().size() > 0) {
                        Boolean sairMenuCliente = false;
                        cliente cli = new cliente();
                        utilitario.ImprimaMensagem("*                          Olá cliente!                         *",
                                                   "*               Seja bem-vindo(a) ao Supermercado               *");

                        do{
                            Boolean sairMenuEscolhaDeCaixas = false;
                            System.out.println(" ( 1 ) Escolher produtos \n ( 2 ) Comprar \n ( 3 ) Consultar preço \n ( 4 ) Consultar estoque de produtos \n ( 5 ) Ver Carrinho de Compras \n ( 0 ) Sair");
                            int opcaoCliente = scanner.nextInt();
                            double quantidade = 0;
                            switch (opcaoCliente){
                                case 1:
                                    boolean continuarComprando = true;
                                     utilitario.ImprimaMensagem(
                                             "*          ( 0 ) Para voltar ao menu a qualquer momento!        *",
                                             "*          ( 1 ) Para Cancelar a compra!                        *");
                                    do{
                                        System.out.println("Digite o código do produto");
                                        String codigo = scanner.next();
                                            if(codigo.equals("0")){
                                                    break;
                                            }
                                            if(codigo.equals("1")){
                                                cli.getCarrinho().devolverProdutosCarrinho();
                                                break;
                                            }
                                            /* --> Função: 
                                             		.Busca o produto pelo código e verificaa disponibilidade.
                                            */
                                        Produto p = estoqueDeProdutos.seekProduto(codigo);
                                        if(p != null){

                                            boolean verificacao;
                                            if(p instanceof ProdutoUnitario){
                                                System.out.println("Digite a quantidade de "+p.getNome().toUpperCase()+": "); 
                                                quantidade = scanner.nextInt();
                                                if(quantidade == 0){
                                                    break;
                                                }
                                                verificacao = estoqueDeProdutos.produtoParaCompra(codigo, quantidade, true);
                                                if(verificacao){
                                                    cli.getCarrinho().addProduto(p, quantidade);
                                                    estoqueDeProdutos.removerProduto(codigo, quantidade);
                                                }  
                                            }
                                            if(p instanceof ProdutoQuilo){
                                                System.out.println("Digite a quantida de quilos de "+p.getNome().toUpperCase()+": "); 
                                                quantidade = scanner.nextDouble();
                                                /* --> Função: 
                                         				.Verifica a quantidade do produto.
                                                */
                                                verificacao = estoqueDeProdutos.produtoParaCompra(codigo, quantidade, true);
                                                if(verificacao){
                                                    ProdutoQuilo pkg = (ProdutoQuilo) p;
                                                    cli.getCarrinho().addProduto(p, quantidade);
                                                    estoqueDeProdutos.removerProduto(codigo, quantidade);
                                                } 
                                            }                                        
                                        } else{
                                            System.out.println("Produto de código " + codigo + " não encontrado no estoque.");   
                                        }

                                        if(codigo.equals(0) || quantidade == 0 || quantidade == 1 || codigo.equals(1)){
                                            continuarComprando = false;
                                            if(codigo.equals(1)){
                                                cli.getCarrinho().devolverProdutosCarrinho();
                                            }
                                        }
                                        scanner.nextLine();
                                    }while(continuarComprando);

                                    sairMenuCliente = false;
                                    break;
                                case 2:
                                	/* --> Função: 
                     						.Verifica se o cliente possui itens no carrinho
                     							|--> Caso não tenha, ele não prossegue com a listagem.
                                	 */
                                    if (cli.getCarrinho().verificaCarrinho()){
                                        int opcaoCaixaCompra;
                                        List<caixa> caixasDisponiveis = ObtenhaCaixasDisponiveis();
                                        utilitario.ImprimaMensagem("*                           CAIXAS                              *");
                                        do{
                                            utilitario.ImprimaMensagem("*                    Selecione um caixa                         *");
                                            MostrarCaixasEmFuncionamento();
                                            opcaoCaixaCompra = scanner.nextInt();

                                            if (opcaoCaixaCompra > 0 && opcaoCaixaCompra <= caixasDisponiveis.size()) {
                                                caixa caixaSelecionado = caixasDisponiveis.get(Integer.valueOf(opcaoCaixaCompra)-1);

                                                venda venda = caixaSelecionado.iniciarVenda(cli);

                                                sairMenuCliente = true;
                                                opcaoCaixaCompra = 0;
                                                utilitario.ImprimaMensagem("*           Obrigado por comprar conosco! Volte sempre!         *");
                                            }
                                        }while(opcaoCaixaCompra != 0);
                                    }else {
                                        utilitario.ImprimaMensagem("*  Seu carrinho de compras está vazio. Escolha alguns produtos  *");
                                        sairMenuEscolhaDeCaixas = true;
                                    }
                                    break;
                                case 3: // --> Função: Para consulta de preços.
                                    utilitario.ImprimaMensagem("*                   Informe o código do produto                 *");
                                    String codigo = scanner.next();
                                    cli.consultarValor(codigo);
                                    break;
                                case 4: // --> Função: Mostrar estoque do mercado.
                                    estoqueDeProdutos.mostrarEstoque(1);
                                    break;
                                case 5: // --> Função: Mostrar Carrinho de compras.
                                    utilitario.ImprimaMensagem("*                    Seu Carrinho de Compras                    *");
                                    cli.getCarrinho().exibirCarrinhoCliente();
                                    break;
                                case 0: 
                                    sairMenuCliente = true;
                                    cli.getCarrinho().devolverProdutosCarrinho();
                                    break;
                                default:
                                    break;
                            }
                        }while(!sairMenuCliente);
                    }else {
                        utilitario.ImprimaMensagem("*                Nenhum caixa está funcionando!                 *");
                        break;
                    }
                    
                    scanner.nextLine();
                    break;
                case 0:
                    sairMenu = true;
                    break;
                default:
                    break;
            }
        }while(!sairMenu);
    }
   
    // --> Função: Método responsável por mostrar a mensagem de saudação do sistema.
    private static void Saudacao() {
        utilitario.ImprimaMensagem("*   Bem vindo ao Sistema de Controle e Vendas do Supermercado   *");
        utilitario.Continuar();
    }

    // --> Função: Método responsável por realizar a carga iniicial do estoque de produtos.
    private static void Feed() {
        estoqueDeProdutos.Feed();
        utilitario.Continuar();
    }
    
    // --> Função: Método responsável por declarar os funcionários.
    private static void CriarFuncionarios(){
        gerente gerente = new gerente("GERENTE DO MERCADO", "admin", "admin" );
        operadorDeCaixa funcionario1 = new operadorDeCaixa("Thiago", "f1", "1111" );
        operadorDeCaixa funcionario2 = new operadorDeCaixa("Sophia", "f2", "1122"  );
        operadorDeCaixa funcionario3 = new operadorDeCaixa("Cleiton", "f3", "1133" );
        operadorDeCaixa funcionario4 = new operadorDeCaixa("Humberto", "f4", "1144"  );
        operadorDeCaixa funcionario5 = new operadorDeCaixa("Cilena","f5", "1155" );

        funcionarios.add(gerente);         
        funcionarios.add(funcionario1);     
        funcionarios.add(funcionario2);     
        funcionarios.add(funcionario3);     
        funcionarios.add(funcionario4);
        funcionarios.add(funcionario5);

        //--> Função: declara os caixas do supermercado
        caixa c1 = new caixa(01);
        caixa c2 = new caixa(02);
        caixa c3 = new caixa(03);
        
        caixas.add(c1); //[0]
        caixas.add(c2); //[1]
        caixas.add(c3); //[2]
    }
    
    // --> Função: Controla o menu do gerente
    private static void ControleMenuGerente(gerente gerente, int tentativas, int chances) {
        utilitario.ImprimaMensagem("*                             LOGIN                             *");
        Boolean acessou = false;
        
        do{
            System.out.println("Senha: ");
            String senha = scanner.next();
            
            if (gerente.getSenha().equals(senha)) {
                acessou = true;
                
                utilitario.ImprimaMensagem("              Bem vindo, " + gerente.getNome() + "!              ");
                MenuGerente(gerente);
            }
            else{
                System.out.println("Senha incorreta.");
                tentativas++;
            }
        }while(tentativas < chances && !acessou);
        
        if (tentativas >= chances && !acessou) {
            System.out.println("As tentativas de login acabaram.\nSaindo...");
            scanner.nextLine();
        }
    }
    
    // --> Função: Opções do menu de gerente.
    private static void MenuGerente(gerente gerente) {
        Boolean sairMenuGer = false;
        do{
            MostrarMenuGerente();
            int opcaoFunGer = scanner.nextInt();
            
            switch(opcaoFunGer){
                case 1:
                    MenuGerenteAdicionarProduto(gerente);
                    break;
                case 2: 
                    MenuGerenteRemoverProduto(gerente);
                    break;
                case 3: // emitir relatorio estoque
                    gerente.emitirRelatorioDeEstoque();
                    break;
                case 4: // emitir relatorio vendas
                    gerente.emitirRelatorioDeVendas(caixas);
                    break;
                case 0:
                    sairMenuGer = true;
                    break;
                default:
                    break;
            }
            scanner.nextLine();
        }while(!sairMenuGer);
    }

    private static void MenuGerenteRemoverProduto(gerente gerente) {
        // --> Função: Remover produto
        utilitario.ImprimaMensagem("*                        Remover produtos                       *");
        System.out.println("Digite o código do produto para remover:");
        String codigoProduto  = scanner.next();
        Produto p = estoqueDeProdutos.seekProduto(codigoProduto);
        if(p != null){
            boolean verificacao;
            if(p instanceof ProdutoUnitario){
                System.out.println("Digite a quantidade de "+p.getNome().toUpperCase()+": "); 
                int quantidade = scanner.nextInt();
                if(quantidade == 0){
                    return;
                }
                verificacao = estoqueDeProdutos.produtoParaCompra(codigoProduto, quantidade, false);
                if(verificacao){
                    estoqueDeProdutos.removerProduto(codigoProduto, quantidade);
                    System.out.println(quantidade + " unidades de " + p.getNome() + " foram removidos do estoque.");
                    utilitario.Continuar();
                }  
            }
            if(p instanceof ProdutoQuilo){
                System.out.println("Digite a quantida de quilos de " +p.getNome().toUpperCase()+ ": "); 
                double quantidade = scanner.nextDouble();
                // --> Função: Verifica a quantidade do produto em específico.
                verificacao = estoqueDeProdutos.produtoParaCompra(codigoProduto, quantidade, false);
                if(verificacao){
                    ProdutoQuilo pkg = (ProdutoQuilo) p;
                    estoqueDeProdutos.removerProduto(codigoProduto, quantidade);
                    System.out.println(quantidade + " quilos de " + p.getNome() + " foram removidos do estoque.");
                    utilitario.Continuar();
                } 
            }
        }
    }
    
    // --> Função: Método responsável por mostrar o menu do gerente.
    private static void MostrarMenuGerente(){
        utilitario.ImprimaMensagem("*                      Menu de Gerente                          *");
        System.out.println(" ( 1 ) Adicionar produto no estoque \n ( 2 ) Remover produto \n ( 3 ) Emitir relatório de estoque \n ( 4 ) Emitir relatório de vendas \n ( 0 ) Logout ");
        System.out.println();
    }
    
    // --> Função: Método responsável por mostrar o menu para o gerente adicionar o produto.
    private static void MenuGerenteAdicionarProduto(gerente gerente) {
        // --> Função: Adicionar o produto.
        Boolean sairMenuProduto = false;
        
        do{
            utilitario.ImprimaMensagem("*    Qual o tipo do produto que deseja adicionar ao estoque?    *");
            System.out.println(" ( 1 ) Produto unidade \n ( 2 ) Produto quilo \n ( 0 ) Sair");
            int tipoProduto  = scanner.nextInt();
            
            switch (tipoProduto){
                case 1:
                    WizardAddProdutoUnidade(gerente);
                    break;
                case 2:
                    WizardAddProdutoQuilo(gerente);
                    break;
                case 0:
                    sairMenuProduto = true;
                    break;
                default:
                    break;
            }
        }while(!sairMenuProduto);
    }
    
    // --> Função: Mostrar a escolha dos caixas.
    private static void MostrarMenuListaDeCaixas(List<caixa> caixas){
        utilitario.ImprimaMensagem("*                           CAIXAS                              *");
        Iterator i = caixas.iterator();
        int op = 1;
        while (i.hasNext()) {
            caixa caixa = (caixa)i.next();
            if (caixa.getOperadorCaixa() ==  null) { // se não tiver operador setado
                System.out.print(" ( " + op + " ) " + caixa +"\n");
            }
            else{
                System.out.print(" ( " + op + " ) Logout " + caixa +" ("+caixa.getOperadorCaixa().getNome()+")\n");
            }    
            op++;
        }
        System.out.println(" ( 0 ) Sair");
        System.out.println("*****************************************************************");
    }
    
    // --> Função: Método repsonsável por identificar se existe usuário logado em algum dos caixas.
    private static caixa FuncionarioLogado(String senha){
        return (caixa)caixas.stream().filter(c->c.getOperadorCaixa() != null && c.getOperadorCaixa().getSenha().equals(senha)).findFirst().orElse(null);
    }
    
    // --> Função: Método responsável por obter a lista de caixas disponíveis.
    private static List<caixa> ObtenhaCaixasDisponiveis(){
        return caixas.stream().filter(c->c.getOperadorCaixa() != null).collect(Collectors.toList());
    }
    
    // --> Função: Método responsável por mostrar caixas com operadores logados.
    private static void MostrarCaixasEmFuncionamento(){
        if (ObtenhaCaixasDisponiveis().isEmpty()) {
            System.out.println("Nenhum caixa está atendendo no momento. =(");
        }
        else {
            Iterator i = ObtenhaCaixasDisponiveis().iterator();
            int op = 1;
            while (i.hasNext()) {
                caixa caixa = (caixa)i.next();
                System.out.print(" ( " + op + " ) " + caixa +"\n");
                op++;
            }
            System.out.println(" ( 0 ) Sair");
        }
    }
    
    // --> Função: Método responsável por apresentar o menu de cadastro de produtos por unidade.
    private static void WizardAddProdutoUnidade(gerente gerente) {
        utilitario.ImprimaMensagem("*                    ADICIONANDO PRODUTO                        *");
        System.out.println("Código do produto:");
        String codigoProduto  = scanner.next();
        
        Produto produtoEmEstoque = estoqueDeProdutos.seekProduto(codigoProduto);
        
        /* --> Função: 
          		. Caso o codigo do produto já existir no estoque, manipulamos o produto, senão adicionamos um novo.
        */
        if (produtoEmEstoque != null) { 
            if (produtoEmEstoque instanceof ProdutoUnitario) {
                System.out.println("O produto de código " + produtoEmEstoque.getCodigo() + "-" + produtoEmEstoque.getNome() + " já existe no estoque!");
                AddProdutoUnitario(produtoEmEstoque, gerente);
            }
            else{
                System.out.println("Esse código corresponde a um produto do tipo 'quilo' (" + produtoEmEstoque.getNome() + "). Deseja continuar?");
                System.out.println(" ( 1 ) Sim \n ( 2 ) Não ");
                int opcaoContinuar  = scanner.nextInt();
                switch(opcaoContinuar){
                    case 1:
                        AddProdutoQuilo(produtoEmEstoque, gerente);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("Nome do produto:");
            String nomeProduto  = scanner.next();
            System.out.println("Preço da unidade do produto (exemplo: 99,99):");
            double precoProduto  = scanner.nextDouble();
            System.out.println("Quantidade de itens:");
            int quantidadeProduto  = scanner.nextInt();
            ProdutoUnitario produto = new ProdutoUnitario(codigoProduto + "0", nomeProduto, precoProduto);
            System.out.println("Adicionado " + quantidadeProduto + " unidades do produto: '" + produto.getCodigo() + "- " + produto.getNome() + "'");
            gerente.adicionarProduto(produto, quantidadeProduto);
        }
    }
    
    // --> Função:  Método responsável por apresentar o menu de cadastro de produtos por quilo.
    private static void WizardAddProdutoQuilo(gerente gerente) {
        utilitario.ImprimaMensagem("*                    ADICIONANDO PRODUTO                        *");
        System.out.println("Código do produto:");
        String codigoProduto  = scanner.next();
        
        Produto produtoEmEstoque = estoqueDeProdutos.seekProduto(codigoProduto);
        /* --> Função: 
          		.Caso o codigo do produto já existir no estoque, manipulamos o produto, senão adicionamos um novo. */
        if (produtoEmEstoque != null) { 
            if (produtoEmEstoque instanceof ProdutoQuilo) {
                System.out.println("O produto de código " + produtoEmEstoque.getCodigo() + "-" + produtoEmEstoque.getNome() + " já existe no estoque!");
                AddProdutoQuilo(produtoEmEstoque, gerente);
            }
            else{
                System.out.println("Esse código corresponde a um produto do tipo 'unidade'. Deseja continuar?");
                System.out.println(" ( 1 ) Sim \n ( 2 ) Não ");
                int opcaoContinuar  = scanner.nextInt();
                switch(opcaoContinuar){
                    case 1:
                        AddProdutoUnitario(produtoEmEstoque, gerente);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("Nome do produto:");
            String nomeProduto  = scanner.next();
            System.out.println("Preço do quilo de " + nomeProduto + " (exemplo: 99,99):");
            double precoProduto  = scanner.nextDouble();
            System.out.println("Quilos de " + nomeProduto + ":");
            double quantidadeProduto  = scanner.nextDouble();
            ProdutoQuilo produto = new ProdutoQuilo(codigoProduto + "0", nomeProduto, precoProduto, quantidadeProduto);
            System.out.println("Adicionado " + quantidadeProduto + " quilos de: '" + produto.getCodigo() + "- " + produto.getNome() + "'");
            gerente.adicionarProduto(produto, 0);
        }
    }
    
    private static void AddProdutoUnitario(Produto produtoEmEstoque, gerente gerente) {
        System.out.println("Deseja adicionar quantos itens?");
        int quantidadeProduto  = scanner.nextInt();
        System.out.println("Adicionado " + quantidadeProduto + " unidades do produto: '" + produtoEmEstoque.getCodigo() + "- " + produtoEmEstoque.getNome() + "'");
        gerente.adicionarProduto(produtoEmEstoque, quantidadeProduto);
        utilitario.Continuar();
    }
    
    private static void AddProdutoQuilo(Produto produtoEmEstoque, gerente gerente) {
        System.out.println("Deseja adicionar quantos quilos de " + produtoEmEstoque.getNome() + "?");
        double quantidadeProduto  = scanner.nextDouble();
        System.out.println("Adicionado " + quantidadeProduto + " quilos de: '" + produtoEmEstoque.getCodigo() + "- " + produtoEmEstoque.getNome() + "'");
        gerente.adicionarProduto(produtoEmEstoque, quantidadeProduto);
        utilitario.Continuar();
    }
    
    public static void caixasDisponiveis(){
        System.out.println("/************************************************************/");
        System.out.println("/**            ESCOLHA UM CAIXA PARA A COMPRA              **/");
        System.out.println("/************************************************************/");
        CriarFuncionarios();
        MostrarCaixasEmFuncionamento();
    }
    
}