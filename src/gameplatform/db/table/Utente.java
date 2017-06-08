package gameplatform.db.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente implements java.io.Serializable{
	private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String password;
    private String email;
    private Integer exp_tot;
    private boolean ban;
    private int numeroAccessi;
    private Gruppo gruppo; 
    private Set<Livello> livello = new HashSet<Livello>(0);
    private Set<Giocare> giocare = new HashSet<Giocare>(0);
    private Set<Trofeo> trofeo = new HashSet<Trofeo>(0);
    
    public Utente(){
    	
    }

    public Utente(String nome, String cognome, int eta, String username, String password, String email, Integer exp_tot, boolean ban, int numeroAccessi) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ban = ban;
        this.exp_tot = exp_tot;
        this.numeroAccessi = numeroAccessi;
    }

    @Column(name = "nome", unique = false, nullable = false)
    public String getNome() {
        return nome;
    }

    @Column(name = "cognome", unique = false, nullable = false)
    public String getCognome() {
        return cognome;
    }

    @Column(name = "eta", unique = false, nullable = false)
    public int getEta() {
        return eta;
    }

    @Id
    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(name = "password", unique = false, nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "exp_tot", unique = false, nullable = false)
    public Integer getExp_tot() {
        return exp_tot;
    }

    @Column(name = "ban", unique = false, nullable = false)
    public boolean getBan() {
		return ban;
	}

	@Column(name = "numaccessi", unique = false, nullable = false)
    public int getNumeroAccessi() {
        return numeroAccessi;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nomeGruppo", nullable = false)
    public Gruppo getGruppo(){
    	return this.gruppo;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "utente")
	public Set<Livello> getLivello() {
		return livello;
	}
    
    @OneToMany(mappedBy = "pk.utente", cascade = CascadeType.ALL)
	public Set<Giocare> getGiocare() {
		return giocare;
	}
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "utenteTrofeo", joinColumns = {
			@JoinColumn(name = "username", nullable = false, updatable = true) },
			inverseJoinColumns = { @JoinColumn(name = "nome",
					nullable = false, updatable = true) })
    public Set<Trofeo> getTrofeo() {
		return trofeo;
	}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExp_tot(Integer exp_tot) {
        this.exp_tot = exp_tot;
    }
    
	public void setBan(boolean ban) {
		this.ban = ban;
	}

    public void setNumeroAccessi(int numeroAccessi) {
        this.numeroAccessi = numeroAccessi;
    }
    
    public void setGruppo(Gruppo gruppo){
    	this.gruppo = gruppo;
    }
	
	public void setLivello(Set<Livello> livello) {
		this.livello = livello;
	}
	
	public void setGiocare(Set<Giocare> giocare) {
		this.giocare = giocare;
	}
	
	public void setTrofeo(Set<Trofeo> trofeo) {
		this.trofeo = trofeo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (ban != other.ban)
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (eta != other.eta)
			return false;
		if (exp_tot == null) {
			if (other.exp_tot != null)
				return false;
		} else if (!exp_tot.equals(other.exp_tot))
			return false;
		if (giocare == null) {
			if (other.giocare != null)
				return false;
		} else if (!giocare.equals(other.giocare))
			return false;
		if (gruppo == null) {
			if (other.gruppo != null)
				return false;
		} else if (!gruppo.equals(other.gruppo))
			return false;
		if (livello == null) {
			if (other.livello != null)
				return false;
		} else if (!livello.equals(other.livello))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroAccessi != other.numeroAccessi)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (trofeo == null) {
			if (other.trofeo != null)
				return false;
		} else if (!trofeo.equals(other.trofeo))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}

