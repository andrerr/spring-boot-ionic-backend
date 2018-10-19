package com.andrerocha.cursomc.domain;

import javax.persistence.Entity;

import com.andrerocha.cursomc.domain.enums.StatusPagamento;

@Entity
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartao() {}

	public PagamentoCartao(Integer id, StatusPagamento statusPagamento, Integer numeroParcelas) {
		super();
		this.setNumeroParcelas(numeroParcelas);
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
