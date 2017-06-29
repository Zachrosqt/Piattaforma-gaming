package gameplatform.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameplatform.business.impl.UtenteGiocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

public interface GameplatformService {
	
	List<Template> templates(String nomePagina);
	boolean login(String username, String Password, HttpServletRequest request);
	void logout(Utente user, HttpSession session);
	boolean registration(Utente user);
	boolean permControl(Utente user, String pageName);
	List<Gioco> allGames();
	List<Gioco> game(String game);
	List<Immagine> allImmages(String game);
	List<UtenteGiocare> recensioni(String game);
	
}
