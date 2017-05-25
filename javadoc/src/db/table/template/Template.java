package db.table.template;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class Template implements java.io.Serializable{

	private String indirizzo;
	private Set<PermessoTemplate> permessoTemplate = new HashSet<PermessoTemplate>(0);
	
	public Template(){
		
	}
	
	public Template(String indirizzo){
		this.indirizzo = indirizzo;
	}
	
	public Template(String indirizzo, Set<PermessoTemplate> permessoTemplate){
		this.indirizzo = indirizzo;
		this.permessoTemplate = permessoTemplate;
	}
	
	@Id
	@Column(name = "TemplateNome", unique = true, nullable = false)
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@OneToMany(mappedBy = "pk.template", cascade = CascadeType.ALL)
	public Set<PermessoTemplate> getPermessoTemplate() {
		return permessoTemplate;
	}
	public void setPermessoTemplate(Set<PermessoTemplate> permessoTemplate) {
		this.permessoTemplate = permessoTemplate;
	}
	
}
