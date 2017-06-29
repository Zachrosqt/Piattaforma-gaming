package gameplatform.business.impl;

import gameplatform.db.table.Giocare;
import gameplatform.db.table.Utente;

public class UtenteGiocare {
	
	private Utente user;
	private Giocare game;
	
	public UtenteGiocare(){
		
	}
	
	public UtenteGiocare(Utente user, Giocare game){
		this.setUser(user);
		this.setGame(game);
	}

	public Utente getUser() {
		return user;
	}

	public void setUser(Utente user) {
		this.user = user;
	}

	public Giocare getGame() {
		return game;
	}

	public void setGame(Giocare game) {
		this.game = game;
	}

}
