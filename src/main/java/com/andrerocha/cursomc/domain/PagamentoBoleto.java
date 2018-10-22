package com.andrerocha.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.andrerocha.cursomc.domain.enums.StatusPagamento;

@Entity
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	public PagamentoBoleto() {}
	

	public PagamentoBoleto(Integer id, StatusPagamento statusPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, statusPagamento, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "PagamentoBoleto [dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento + "]";
	}
}
