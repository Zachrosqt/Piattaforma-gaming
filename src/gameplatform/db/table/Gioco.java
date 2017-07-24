package gameplatform.db.table;

import java.util.Calendar;
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
@Table(name = "gioco")
public class Gioco implements java.io.Serializable{
	private String nome;
    private String descrizione;
    private String specifiche;
    private int mediaGioco;
    private Calendar date = Calendar.getInstance();
    private Set<Giocare> giocare = new HashSet<Giocare>(0);
    private Set<Trofeo> trofeo = new HashSet<Trofeo>(0);
    private Set<Immagine> immagine = new HashSet<Immagine>(0);
    private Categoria categoria;
    
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
    
    @Column(name = "media", unique = false, nullable = false)
    public int getMediaGioco() {
		return mediaGioco;
	}

	public void setMediaGioco(int mediaGioco) {
		this.mediaGioco = mediaGioco;
	}
	
	@Column(name = "date", unique = false, nullable = false)
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
    
    @OneToMany(mappedBy = "pk.gioco", cascade = CascadeType.ALL)
	public Set<Giocare> getGiocare() {
		return giocare;
	}

	public void setGiocare(Set<Giocare> giocare) {
		this.giocare = giocare;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "gioco")
	public Set<Trofeo> getTrofeo() {
		return trofeo;
	}

	public void setTrofeo(Set<Trofeo> trofeo) {
		this.trofeo = trofeo;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "gioco")
	public Set<Immagine> getImmagine() {
		return immagine;
	}

	public void setImmagine(Set<Immagine> immagine) {
		this.immagine = immagine;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria", nullable = true)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


}
