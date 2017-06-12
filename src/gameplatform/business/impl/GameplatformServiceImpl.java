package gameplatform.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.db.table.Template;

public class GameplatformServiceImpl implements GameplatformService{
	
	private volatile static GameplatformServiceImpl istanza = null;
	
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
		
		GameplatformCRUD crud = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		
		List<?> template = crud.executeQuery("from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = '" + nomePagina + "' order by joinPage.priority");
		
		Iterator<?> it = template.iterator();

        while (it.hasNext()) { 
        	Object[] obj = (Object[]) it.next();
        	Template temp = (Template) obj[0];  ;
        	listTemplate.add(temp);
        }
        
        return listTemplate;
	}

	@Override
	public boolean login(String username, String Password) {
		return false;
	}

	@Override
	public boolean logout(String username) {
		return false;
	}

}
