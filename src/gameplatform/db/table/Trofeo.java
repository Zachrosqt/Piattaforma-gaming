package gameplatform.db.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	    private String obbiettivo;
	    private String icona;
	    private String nome;
	    private Set<Utente> utente = new HashSet<Utente>(0); 
	    private Gioco gioco;
	    
	    public Trofeo(){
	    	
	    }

	    public Trofeo(String obiettivo, String icona, String nome) {
	        this.obbiettivo = obiettivo;
	        this.icona = icona;
	        this.nome = nome;
	    }

	    @Column(name = "obiettivo", unique = false, nullable = false)
	    public String getObiettivo() {
	        return obbiettivo;
	    }

	    public void setObiettivo(String obiettivo) {
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
	    
	    @ManyToMany(cascade=CascadeType.ALL, mappedBy = "trofeo")
	    public Set<Utente> getUtente() {
			return utente;
		}

		public void setUtente(Set<Utente> utente) {
			this.utente = utente;
		}
		
		@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "game", nullable = false)
		public Gioco getGioco() {
			return gioco;
		}

		public void setGioco(Gioco gioco) {
			this.gioco = gioco;
		}
		
}
