package gameplatform.business;

import java.util.List;

import gameplatform.db.table.Template;

public interface gameplatformService {
	
	List<Template> templates(String nomePagina);
	boolean login(String username, String Password);
	boolean logout(String username);
	
}
