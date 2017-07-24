package gameplatform.business;

import java.util.List;

import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Utente;

public interface GameplatformGAME {

	List<Gioco> allGames();
	List<Utente> username(String username);
	List<Giocare> playGame(String gioco, String username);
	List<Livello> livelliList (String usarname);
	int sumUserExp(String username);
	void startGiocare(String gioco, String username);
	void updateGameplay(Giocare game);
	void addLv(Livello lv);
	void newTrofeoUser(String nome, String username);
	boolean giocareAtGame(String gioco, String username);
}
