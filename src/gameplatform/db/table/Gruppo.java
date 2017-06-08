package gameplatform.db.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gruppo")
public class Gruppo implements java.io.Serializable{
	private String nome;
	private Set<Utente> utente = new HashSet<Utente>(0);
	private Set<Permesso> permesso = new HashSet<Permesso>(0);

	public Gruppo(){
		
	}
	
    public Gruppo(String nome) {
        this.nome = nome;
    }

    @Id
    @Column(name = "nome", unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gruppo")
    public Set<Utente> getUtente() {
		return utente;
	}
	public void setUtente(Set<Utente> utente) {
		this.utente = utente;
	}
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "permessoGruppo", joinColumns = {
			@JoinColumn(name = "nome", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "PageNome",
					nullable = false, updatable = false) })
	public Set<Permesso> getPermesso() {
		return permesso;
	}

	public void setPermesso(Set<Permesso> permesso) {
		this.permesso = permesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permesso == null) ? 0 : permesso.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
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
		Gruppo other = (Gruppo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permesso == null) {
			if (other.permesso != null)
				return false;
		} else if (!permesso.equals(other.permesso))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}
	
}
