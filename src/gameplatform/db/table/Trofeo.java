package gameplatform.db.table;

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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((gioco == null) ? 0 : gioco.hashCode());
			result = prime * result + ((icona == null) ? 0 : icona.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result + obbiettivo;
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
			Trofeo other = (Trofeo) obj;
			if (gioco == null) {
				if (other.gioco != null)
					return false;
			} else if (!gioco.equals(other.gioco))
				return false;
			if (icona == null) {
				if (other.icona != null)
					return false;
			} else if (!icona.equals(other.icona))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (obbiettivo != other.obbiettivo)
				return false;
			if (utente == null) {
				if (other.utente != null)
					return false;
			} else if (!utente.equals(other.utente))
				return false;
			return true;
		}
		
}
