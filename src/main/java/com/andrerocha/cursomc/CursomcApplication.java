package com.andrerocha.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrerocha.cursomc.domain.Categoria;
import com.andrerocha.cursomc.domain.Cidade;
import com.andrerocha.cursomc.domain.Cliente;
import com.andrerocha.cursomc.domain.Endereco;
import com.andrerocha.cursomc.domain.Estado;
import com.andrerocha.cursomc.domain.ItemPedido;
import com.andrerocha.cursomc.domain.Pagamento;
import com.andrerocha.cursomc.domain.PagamentoBoleto;
import com.andrerocha.cursomc.domain.PagamentoCartao;
import com.andrerocha.cursomc.domain.Pedido;
import com.andrerocha.cursomc.domain.Produto;
import com.andrerocha.cursomc.domain.enums.StatusPagamento;
import com.andrerocha.cursomc.domain.enums.TipoCliente;
import com.andrerocha.cursomc.repositories.CategoriaRepository;
import com.andrerocha.cursomc.repositories.CidadeRepository;
import com.andrerocha.cursomc.repositories.ClienteRepository;
import com.andrerocha.cursomc.repositories.EnderecoRepository;
import com.andrerocha.cursomc.repositories.EstadoRepository;
import com.andrerocha.cursomc.repositories.ItemPedidoRepository;
import com.andrerocha.cursomc.repositories.PagamentoRepository;
import com.andrerocha.cursomc.repositories.PedidoRepository;
import com.andrerocha.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Categoria cat1 = new Categoria(null, "INFORMATICA");
		Categoria cat2 = new Categoria(null, "ESCRITORIO");
		Categoria cat3 = new Categoria(null, "CAMA MESA E BANHO");
		Categoria cat4 = new Categoria(null, "ELETRONICOS");
		Categoria cat5 = new Categoria(null, "JARDINAGEM");
		Categoria cat6 = new Categoria(null, "DECORACAO");
		Categoria cat7 = new Categoria(null, "PERFUMARIA");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 1000.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 99.00);
		Produto p6 = new Produto(null, "Sofá", 1290.00);
		Produto p7 = new Produto(null, "Tv blue 45", 1599.00);
		Produto p8 = new Produto(null, "Tapete", 599.00);
		Produto p9 = new Produto(null, "Luminaria", 59.00);
		Produto p10 = new Produto(null, "Microondas", 199.00);
		Produto p11 = new Produto(null, "Ferro de passar", 32.00);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Cliente cli1 = new Cliente(null, "Patricia Lavinia", "patricia@gmail.com", "0124732536767", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32234567", "989843646"));
		
		Endereco e1 = new Endereco(null, "Rua Ronan Alves", "50", "Casa", "Luizote", "38400-000", cli1, c1); 
		Endereco e2 = new Endereco(null, "Av. Otavio Leite", "15", "Apto", "Centro", "38400-454", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 10:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 22:00"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoCartao(null, StatusPagamento.QUITADO, ped1, 2);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("10/10/2018 22:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save((Arrays.asList(pgto1, pgto2)));
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ItemPedido ip1 = new ItemPedido(ped1, p3, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.50, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	}
} 