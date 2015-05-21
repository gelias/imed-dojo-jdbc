package br.edu.imed.model;

public class Customer {
	
	private Long codigo;
	private String name;
	
	public Customer(Long codigo, String name) {
		this.codigo = codigo;
		this.name = name;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
