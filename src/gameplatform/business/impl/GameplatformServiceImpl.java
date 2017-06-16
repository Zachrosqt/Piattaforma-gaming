package gameplatform.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
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
	public boolean logout(String username) {
		return false;
	}

	@Override
	public boolean registration(Utente user, HttpServletRequest request) {
		
		try{
			
			user.setNumeroAccessi(user.getNumeroAccessi() + 1 );
			crud.saveOrUpdate(user);
			return true;
			
		} catch(Exception e){
			
			return false;
			
		}
		
	}

}
