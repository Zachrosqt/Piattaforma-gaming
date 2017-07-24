package gameplatform.db.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "immagine")
public class Immagine implements java.io.Serializable{

	private int id;
	private String path;
	private Gioco gioco;
	
	@Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "path", unique = false, nullable = false)
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game", nullable = false)
	public Gioco getGioco() {
		return gioco;
	}

	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}
	
}
