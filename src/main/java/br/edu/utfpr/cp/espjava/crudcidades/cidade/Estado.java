package br.edu.utfpr.cp.espjava.crudcidades.cidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Estado {
	
	@NotBlank(message = "{app.estado.blank}")
	@Size(min = 5, max = 60, message = "{app.estado.size}")
	private final String nome;
	
	@NotBlank(message = "{app.sigla.blank}")
	@Size(min = 2, max = 2, message = "{app.sigla.size}")
	private final String sigla;
	
	public Estado(final String nome, final String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public EstadoEntidade clonar() {
		var estadoEntidade = new EstadoEntidade();
		
		estadoEntidade.setNome(this.getNome());
		estadoEntidade.setSigla(this.getSigla());
		
		return estadoEntidade;
	}
	
	public static Estado clonar(EstadoEntidade estado) {
		
		return new Estado(estado.getNome(), estado.getSigla());
	}
}
