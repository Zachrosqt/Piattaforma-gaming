package gameplatform.db.table;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gruppo == null) ? 0 : gruppo.hashCode());
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Permesso other = (Permesso) obj;
		if (gruppo == null) {
			if (other.gruppo != null)
				return false;
		} else if (!gruppo.equals(other.gruppo))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permessoTemplate == null) {
			if (other.permessoTemplate != null)
				return false;
		} else if (!permessoTemplate.equals(other.permessoTemplate))
			return false;
		return true;
	}
	
}