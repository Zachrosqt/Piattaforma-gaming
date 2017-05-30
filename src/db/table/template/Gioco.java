package db.table.template;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gioco")
public class Gioco implements java.io.Serializable{
	private String nome;
    private String descrizione;
    private String specifiche;
    private Set<Giocare> giocare = new HashSet<Giocare>(0);
    private Set<Trofeo> trofeo = new HashSet<Trofeo>(0);
    private Set<Immagine> immagine = new HashSet<Immagine>(0);
    
    public Gioco(){
    	
    }

    public Gioco(String nome, String descrizione, String specifiche) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.specifiche = specifiche;
    }

    @Id
	@Column(name = "giocoNome", unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "descrizione", unique = false, nullable = false)
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Column(name = "specifiche", unique = false, nullable = false)
    public String getSpecifiche() {
        return specifiche;
    }

    public void setSpecifiche(String specifiche) {
        this.specifiche = specifiche;
    }

    @OneToMany(mappedBy = "pk.gioco", cascade = CascadeType.ALL)
	public Set<Giocare> getGiocare() {
		return giocare;
	}

	public void setGiocare(Set<Giocare> giocare) {
		this.giocare = giocare;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gioco")
	public Set<Trofeo> getTrofeo() {
		return trofeo;
	}

	public void setTrofeo(Set<Trofeo> trofeo) {
		this.trofeo = trofeo;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gioco")
	public Set<Immagine> getImmagine() {
		return immagine;
	}

	public void setImmagine(Set<Immagine> immagine) {
		this.immagine = immagine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((giocare == null) ? 0 : giocare.hashCode());
		result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((specifiche == null) ? 0 : specifiche.hashCode());
		result = prime * result + ((trofeo == null) ? 0 : trofeo.hashCode());
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
		Gioco other = (Gioco) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (giocare == null) {
			if (other.giocare != null)
				return false;
		} else if (!giocare.equals(other.giocare))
			return false;
		if (immagine == null) {
			if (other.immagine != null)
				return false;
		} else if (!immagine.equals(other.immagine))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (specifiche == null) {
			if (other.specifiche != null)
				return false;
		} else if (!specifiche.equals(other.specifiche))
			return false;
		if (trofeo == null) {
			if (other.trofeo != null)
				return false;
		} else if (!trofeo.equals(other.trofeo))
			return false;
		return true;
	}

}
