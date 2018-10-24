package com.andrerocha.cursomc.domain.enums;

public enum StatusPagamento {
	
	PENDENTE (1, "PEDIDO PENDENTE"),
	QUITADO  (2, "PEDIDO QUITADO"),
	CANCELADO(3, "PEDIDO CANCELADO");
	
	private Integer codigo;
	private String descricao;
	
	private StatusPagamento(Integer codigo, String descricao ) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static StatusPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(StatusPagamento x : StatusPagamento.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido " + codigo);
	}
}
