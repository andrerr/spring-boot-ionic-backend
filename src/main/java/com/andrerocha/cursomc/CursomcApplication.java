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

		Categoria cat1 = new Categoria(null, "INFORMATICA");
		Categoria cat2 = new Categoria(null, "ESCRITORIO");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 1000.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Patricia Lavinia", "patricia@gmail.com", "0124732536767", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32234567", "989843646"));
		
		Endereco e1 = new Endereco(null, "Rua Ronan Alves", "50", "Casa", "Luizote", "38400-000", cli1, c1); 
		Endereco e2 = new Endereco(null, "Av. Otavio Leite", "15", "Apto", "Centro", "38400-454", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 10:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 22:00"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoCartao(null, StatusPagamento.QUITADO, ped1, 2);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("10/10/2018 22:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save((Arrays.asList(pgto1, pgto2)));
		
		ItemPedido ip1 = new ItemPedido(ped1, p3, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.50, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
	}
} 