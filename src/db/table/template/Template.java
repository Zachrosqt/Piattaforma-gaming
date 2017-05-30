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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((permessoTemplate == null) ? 0 : permessoTemplate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (permessoTemplate == null) {
			if (other.permessoTemplate != null)
				return false;
		} else if (!permessoTemplate.equals(other.permessoTemplate))
			return false;
		return true;
	}
	
}
