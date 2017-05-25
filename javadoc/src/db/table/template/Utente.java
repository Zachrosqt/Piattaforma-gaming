package db.table.template;

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
    private int numeroAccessi;
    private Gruppo gruppo; 
    private Set<Livello> livello = new HashSet<Livello>(0);
    private Set<Giocare> giocare = new HashSet<Giocare>(0);
    private Set<Trofeo> trofeo = new HashSet<Trofeo>(0);
    
    public Utente(){
    	
    }

    public Utente(String nome, String cognome, int eta, String username, String password, String email, Integer exp_tot, int numeroAccessi) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.email = email;
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

    @Column(name = "numaccessi", unique = false, nullable = false)
    public int getNumeroAccessi() {
        return numeroAccessi;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nomeGruppo", nullable = false)
    public Gruppo getGruppo(){
    	return this.gruppo;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;

        Utente utente = (Utente) o;

        if (getEta() != utente.getEta()) return false;
        if (getNumeroAccessi() != utente.getNumeroAccessi()) return false;
        if (!getNome().equals(utente.getNome())) return false;
        if (!getCognome().equals(utente.getCognome())) return false;
        if (!getUsername().equals(utente.getUsername())) return false;
        if (!getPassword().equals(utente.getPassword())) return false;
        if (!getEmail().equals(utente.getEmail())) return false;
        return getExp_tot().equals(utente.getExp_tot());
    }

    @Override
    public int hashCode() {
        int result = getNome().hashCode();
        result = 31 * result + getCognome().hashCode();
        result = 31 * result + getEta();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getExp_tot().hashCode();
        result = 31 * result + getNumeroAccessi();
        return result;
    }

}

