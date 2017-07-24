package gameplatform.db.table;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "livello", uniqueConstraints = {@UniqueConstraint(columnNames = {"livello", "username"})})
public class Livello implements java.io.Serializable{
	private int id;
    private int livello;
    private Calendar date = Calendar.getInstance();
    private Utente utente;
    
    public Livello(){
    	
    }

    public Livello(int id, int livello, Calendar date) {
        this.id = id;
        this.livello = livello;
        this.date = date;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "livello", unique = false, nullable = false)
    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    @Column(name = "data", unique = false, nullable = false)
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
    public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
