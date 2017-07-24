package gameplatform.db.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria implements java.io.Serializable{

	private int Id;
	private String categoria;
	private Set<Gioco> gioco = new HashSet<Gioco>(0);

	@Id
	@Column(name = "categoria", unique = false, nullable = false)
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Gioco> getGioco() {
		return gioco;
	}

	public void setGioco(Set<Gioco> gioco) {
		this.gioco = gioco;
	}
	
	
}
