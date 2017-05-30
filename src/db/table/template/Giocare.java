package db.table.template;

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
	private Time minuti;
	private int voto;
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

	@Column(name = "minuti", nullable = false)
	public Time getMinuti() {
		return minuti;
	}

	public void setMinuti(Time minuti) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approvato ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (int) (exp ^ (exp >>> 32));
		result = prime * result + ((minuti == null) ? 0 : minuti.hashCode());
		result = prime * result + (int) (numAccessi ^ (numAccessi >>> 32));
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((recensione == null) ? 0 : recensione.hashCode());
		result = prime * result + voto;
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
		Giocare other = (Giocare) obj;
		if (approvato != other.approvato)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (exp != other.exp)
			return false;
		if (minuti == null) {
			if (other.minuti != null)
				return false;
		} else if (!minuti.equals(other.minuti))
			return false;
		if (numAccessi != other.numAccessi)
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (recensione == null) {
			if (other.recensione != null)
				return false;
		} else if (!recensione.equals(other.recensione))
			return false;
		if (voto != other.voto)
			return false;
		return true;
	}

}
