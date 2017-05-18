package db.table.template;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trofeo")
public class Trofeo implements java.io.Serializable{
	    private int obbiettivo;
	    private String icona;
	    private String nome;
	    private Set<Utente> utente = new HashSet<Utente>(0); 
	    private Gioco gioco;
	    
	    public Trofeo(){
	    	
	    }

	    public Trofeo(int obiettivo, String icona, String nome) {
	        this.obbiettivo = obiettivo;
	        this.icona = icona;
	        this.nome = nome;
	    }

	    @Column(name = "obiettivo", unique = false, nullable = false)
	    public int getObiettivo() {
	        return obbiettivo;
	    }

	    public void setObiettivo(int obiettivo) {
	        this.obbiettivo = obiettivo;
	    }

	    @Column(name = "icona", unique = false, nullable = false)
	    public String getIcona() {
	        return icona;
	    }

	    public void setIcona(String icona) {
	        this.icona = icona;
	    }

	    @Id
	    @Column(name = "nome", unique = true, nullable = false)
	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }
	    
	    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "trofeo")
	    public Set<Utente> getUtente() {
			return utente;
		}

		public void setUtente(Set<Utente> utente) {
			this.utente = utente;
		}
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "game", nullable = false)
		public Gioco getGioco() {
			return gioco;
		}

		public void setGioco(Gioco gioco) {
			this.gioco = gioco;
		}

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Trofeo)) return false;

	        Trofeo trofeo = (Trofeo) o;

	        if (obbiettivo != trofeo.obbiettivo) return false;
	        if (getIcona() != null ? !getIcona().equals(trofeo.getIcona()) : trofeo.getIcona() != null) return false;
	        return getNome() != null ? getNome().equals(trofeo.getNome()) : trofeo.getNome() == null;
	    }

	    @Override
	    public int hashCode() {
	        int result = obbiettivo;
	        result = 31 * result + (getIcona() != null ? getIcona().hashCode() : 0);
	        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
	        return result;
	    }

}
