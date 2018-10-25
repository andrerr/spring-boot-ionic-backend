package com.andrerocha.cursomc.domain.enums;

public enum TipoPagamento {

	AVISTA (1, "PAGAMENTO À VISTA"),
	BOLETO (2, "PAGAMENTO BOLETO"),
	PARCELADO (3, "PAGAMENTO PARCELADO");
	
	private TipoPagamento(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	private Integer id;
	private String descricao;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoPagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for(TipoPagamento x : TipoPagamento.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido " + id);
	}
	
}
