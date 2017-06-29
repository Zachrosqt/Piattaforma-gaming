package gameplatform.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

public class GameplatformServiceImpl implements GameplatformService{
	
	private volatile static GameplatformServiceImpl istanza = null;
	private GameplatformCRUD crud = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	
	private GameplatformServiceImpl() {}
	
	public static GameplatformServiceImpl getGameplatformServiceImpl() {
        if (istanza == null) {            
            synchronized (GameplatformServiceImpl.class){
                if (istanza == null)
                    istanza = new GameplatformServiceImpl();
            }
        }
        return istanza;
    }

	@Override
	public List<Template> templates(String nomePagina) {
		
		List<Template> listTemplate = new ArrayList<Template>();
		
		List<?> template = crud.executeQuery("FROM Template template JOIN template.permessoTemplate joinPage WHERE joinPage.pk.permesso = '" + nomePagina + "' ORDER BY joinPage.priority");
		
		Iterator<?> it = template.iterator();

        while (it.hasNext()) { 
        	Object[] obj = (Object[]) it.next();
        	Template temp = (Template) obj[0];  ;
        	listTemplate.add(temp);
        }
        
        return listTemplate;
	}

	@Override
	public boolean login(String username, String password, HttpServletRequest request) {
		
		List<Utente> login = crud.executeQuery("FROM Utente user WHERE user.username = '" + username + "' AND user.password='" + password + "'");
		
		Iterator<Utente> it = login.iterator();
		
		if (it.hasNext()){
			Utente utente = it.next();
			utente.setNumeroAccessi(utente.getNumeroAccessi() + 1 );
			crud.saveOrUpdate(utente);
			HttpSession session =  request.getSession();
			session.setAttribute("utenteGameplatform", utente);
			return true;
		}
			
		return false;
		
	}

	@Override
	public void logout(Utente user, HttpSession session) {
		session.invalidate();
	}

	@Override
	public boolean registration(Utente user) {
		
		try{
			
			user.setNumeroAccessi(user.getNumeroAccessi() + 1 );
			crud.saveOrUpdate(user);
			return true;
			
		} catch(Exception e){
			
			return false;
			
		}
		
	}

	@Override
	public boolean permControl(Utente user,  String pageName) {
		
		List<Object> control = crud.executeQuery("FROM Permesso perm JOIN perm.gruppo permGroup WHERE permGroup.nome = '" + user.getGruppo().getNome() + "' AND perm.nome = '" + pageName + "'");
		
		Iterator<Object> it = control.iterator();
		
		if (it.hasNext()){
			return true;
		} else {
			return false;
		}	
	}

	@Override
	public List<Gioco> allGames() {
		List<Gioco> games = crud.executeQuery("FROM Gioco games ORDER BY games.date DESC");
		
		return games;
	}
	
	@Override
	public List<Gioco> game(String game) {
		List<Gioco> games = crud.executeQuery("FROM Gioco game WHERE game.nome = '" + game + "'");
		
		return games;
	}
	
	@Override
	public List<Immagine> allImmages(String game) {
		List<Immagine> immagine = crud.executeQuery("FROM Immagine img WHERE img.gioco.nome = '" + game + "'");
		
		return immagine;
	}

	@Override
	public List<UtenteGiocare> recensioni(String game) {
		List<UtenteGiocare> review = new ArrayList<UtenteGiocare>();		
		List<?> recensioni = crud.executeQuery("FROM Utente user JOIN user.giocare game WHERE game.pk.gioco.nome = '" + game + "' AND game.approvato = '1'");
		
		Iterator<?> it = recensioni.iterator();
		
		while(it.hasNext()){
			Object[] obj = (Object[]) it.next();
        	Utente user = (Utente) obj[0];
        	Giocare giocare = (Giocare) obj[1];
        	UtenteGiocare userGame = new UtenteGiocare(user, giocare);
        	review.add(userGame);
		}
		
		return review;
	}

}
