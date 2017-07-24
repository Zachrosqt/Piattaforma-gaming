package gameplatform.db.table;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class GiocareId implements java.io.Serializable{
	private Utente utente;
	private Gioco gioco;
	
	@ManyToOne
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	@ManyToOne
	public Gioco getGioco() {
		return gioco;
	}
	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}
}
