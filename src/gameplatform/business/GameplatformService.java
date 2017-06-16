package gameplatform.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

public interface GameplatformService {
	
	List<Template> templates(String nomePagina);
	boolean login(String username, String Password, HttpServletRequest request);
	boolean logout(String username);
	boolean registration(Utente user, HttpServletRequest request);
	
}
