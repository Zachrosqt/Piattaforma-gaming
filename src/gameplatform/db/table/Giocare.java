package gameplatform.db.table;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "giocare")
@AssociationOverrides({
    @AssociationOverride(name = "pk.utente",
        joinColumns = @JoinColumn(name = "username")),
    @AssociationOverride(name = "pk.gioco",
        joinColumns = @JoinColumn(name = "giocoNome")) })
public class Giocare implements java.io.Serializable{
	private GiocareId pk = new GiocareId();
	private long numAccessi;
	private long exp;
	private long minuti;
	private int voto;
	private int livello;
	private String recensione;
	private Calendar data;
	private boolean approvato;
	

	@EmbeddedId
	public GiocareId getPk() {
		return pk;
	}

	public void setPk(GiocareId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Utente getUtente() {
		return getPk().getUtente();
	}

	public void setUtente(Utente utente) {
		getPk().setUtente(utente);
	}
	
	@Transient
	public Gioco getGioco() {
		return getPk().getGioco();
	}

	public void setGioco(Gioco gioco) {
		getPk().setGioco(gioco);
	}

	@Column(name = "numAccessi", nullable = false)
	public long getNumAccessi() {
		return numAccessi;
	}

	public void setNumAccessi(long numAccessi) {
		this.numAccessi = numAccessi;
	}

	@Column(name = "exp", nullable = false)
	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}
	
	@Column(name = "lv", nullable = false)
	public int getLivello() {
		return livello;
	}

	public void setLivello(int livelli) {
		this.livello = livelli;
	}

	@Column(name = "minuti", nullable = false)
	public long getMinuti() {
		return minuti;
	}

	public void setMinuti(long minuti) {
		this.minuti = minuti;
	}

	@Column(name = "voto", nullable = true)
	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	@Column(name = "recensione", nullable = true)
	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	@Column(name = "data_recensione", nullable = true)
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	@Column(name = "recensione_approvata", nullable = true)
	public boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}



}
