package db.table.template;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "permesso")
public class Permesso implements java.io.Serializable{
	
	private String nome;
	private String indirizzo;
	private Set<PermessoTemplate> permessoTemplate = new HashSet<PermessoTemplate>(0);
	private Set<Gruppo> gruppo = new HashSet<Gruppo>(0);  
	
	public Permesso(){
		
	}
	
	public Permesso(String nome, String indirizzo){
		this.nome = nome;
		this.indirizzo = indirizzo;
	}

	public Permesso(String nome, String indirizzo, Set<PermessoTemplate> permessoTemplate){
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.permessoTemplate = permessoTemplate;
	}
	
	@Id
	@Column(name = "PageNome", unique = true, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "PageIndirizzo", unique = true, nullable = false)
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@OneToMany(mappedBy = "pk.permesso", cascade = CascadeType.ALL)
	public Set<PermessoTemplate> getPermessoTemplate() {
		return permessoTemplate;
	}
	public void setPermessoTemplate(Set<PermessoTemplate> permessoTemplate) {
		this.permessoTemplate = permessoTemplate;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permesso")
	public Set<Gruppo> getGruppo() {
		return gruppo;
	}

	public void setGruppo(Set<Gruppo> gruppo) {
		this.gruppo = gruppo;
	}

}
