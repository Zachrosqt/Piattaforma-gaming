package db.table.template;

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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "username")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gruppo)) return false;

        Gruppo gruppo = (Gruppo) o;

        return getNome() != null ? getNome().equals(gruppo.getNome()) : gruppo.getNome() == null;
    }

    @Override
    public int hashCode() {
        return getNome() != null ? getNome().hashCode() : 0;
    }

}
