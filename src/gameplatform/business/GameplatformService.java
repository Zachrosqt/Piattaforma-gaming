package gameplatform.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameplatform.business.impl.UtenteGiocare;
import gameplatform.db.table.Categoria;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

public interface GameplatformService {
	
	List<Template> templates(String nomePagina);
	boolean login(String username, String Password, HttpServletRequest request);
	void logout(Utente user, HttpSession session);
	boolean registration(Utente user, Livello lv);
	boolean permControl(Utente user, String pageName);
	List<Gioco> allGames();
	List<Categoria> allCategorie();
	List<Gioco> game(String game);
	List<Immagine> allImmages(String game);
	List<UtenteGiocare> recensioni(String game);
	List<Utente> username(String username);
	List<Utente> userEmail(String email);
	List<Gruppo> group (String group);
	List<Giocare> allReview(String game);
	String livelli (String usarname);
	int giocare(String usarname);
	boolean insertReview(String review, int voto, String gioco, String username);
	void insertUser(Utente user);
	
	
}
