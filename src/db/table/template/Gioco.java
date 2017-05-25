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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nome")
	public Set<Trofeo> getTrofeo() {
		return trofeo;
	}

	public void setTrofeo(Set<Trofeo> trofeo) {
		this.trofeo = trofeo;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gioco)) return false;

        Gioco gioco = (Gioco) o;

        if (getNome() != null ? !getNome().equals(gioco.getNome()) : gioco.getNome() != null) return false;
        if (getDescrizione() != null ? !getDescrizione().equals(gioco.getDescrizione()) : gioco.getDescrizione() != null)
            return false;
        return getSpecifiche() != null ? getSpecifiche().equals(gioco.getSpecifiche()) : gioco.getSpecifiche() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getDescrizione() != null ? getDescrizione().hashCode() : 0);
        result = 31 * result + (getSpecifiche() != null ? getSpecifiche().hashCode() : 0);
        return result;
    }

}
