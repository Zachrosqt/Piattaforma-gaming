package gameplatform.business.impl;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformGAME;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;

public class GameplatformGAMEImp implements GameplatformGAME{
	
	private volatile static GameplatformGAMEImp istanza = null;
	private GameplatformCRUD crud = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	
	private GameplatformGAMEImp() {}
	
	public static GameplatformGAMEImp getGameplatformGAMEImp() {
        if (istanza == null) {            
            synchronized (GameplatformGAMEImp.class){
                if (istanza == null)
                    istanza = new GameplatformGAMEImp();
            }
        }
        return istanza;
    }

	
	// --------------- Metodi per gioco --------------------------
	
	@Override
	public List<Gioco> allGames() {
		List<Gioco> games = crud.executeQuery("FROM Gioco games ORDER BY games.date DESC");
		
		return games;
	}
	
	@Override
	public List<Utente> username(String username) {
		
		List<Utente> user = crud.executeQuery("FROM Utente user WHERE user.username='" + username + "'");
		return user;
	}
	
	@Override
	public boolean giocareAtGame(String gioco, String username) {
		// TODO Auto-generated method stub
		
		List<Giocare> giocare = crud.executeQuery("FROM Giocare gioca WHERE gioca.pk.utente.username='" + username + "' AND gioca.pk.gioco.nome='" + gioco + "'");
		
		Iterator<Giocare> it = giocare.iterator();
		
		if(it.hasNext()){
			return true;
		}
		
		return false;
	}

	@Override
	public void startGiocare(String gioco, String username) {
		// TODO Auto-generated method stub
		
		Giocare startGioco = new Giocare();
		Gioco gameToPlay = null;
		Utente player = null;
		
		startGioco.setApprovato(false);
		startGioco.setData(Calendar.getInstance());
		startGioco.setExp(0);
		startGioco.setMinuti(0);
		startGioco.setNumAccessi(1);
		
		List<Gioco> game = crud.executeQuery("FROM Gioco game WHERE game.nome='" + gioco + "'");
		
		Iterator<Gioco> gameIt = game.iterator();
		
		if(gameIt.hasNext()){
			gameToPlay = gameIt.next();
		}
		
		List<Utente> user = crud.executeQuery("FROM Utente user WHERE user.username='" + username + "'");
		
		Iterator<Utente> userIt = user.iterator();
		
		if(userIt.hasNext()){
			player = userIt.next();
		}
		
		startGioco.setUtente(player);
		startGioco.setGioco(gameToPlay);
		
		crud.saveOrUpdate(startGioco);
		
	}

	@Override
	public List<Giocare> playGame(String gioco, String username) {
		List<Giocare> giocare = crud.executeQuery("FROM Giocare game WHERE game.pk.gioco.nome = '" + gioco + "' AND game.pk.utente.username='" + username + "'");
		return giocare;
	}

	@Override
	public void updateGameplay(Giocare game) {
		crud.saveOrUpdate(game);
	}

	@Override
	public List<Livello> livelliList(String usarname) {
		// TODO Auto-generated method stub
		
		List<Livello> lv = crud.executeQuery("FROM Livello lv WHERE lv.utente.username='" + usarname + "' ORDER BY lv.livello");
		
		return lv;
	}

	@Override
	public void addLv(Livello lv) {
		// TODO Auto-generated method stub
		crud.saveOrUpdate(lv);
	}

	@Override
	public int sumUserExp(String username) {
		// TODO Auto-generated method stub
		List<Giocare> giocare = crud.executeQuery("FROM Giocare game WHERE game.pk.utente.username='" + username + "'");

		Iterator<Giocare> allExp = giocare.iterator();
		
		int expTot = 0;
		
		while (allExp.hasNext()) {
			Giocare gameTemp = allExp.next();
			
			expTot += gameTemp.getExp();
			
		}
		return expTot;
	}

	@Override
	public void newTrofeoUser(String nome, String username) {
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query queryTrofeo = session.createQuery("FROM Trofeo trf WHERE trf.nome='" + nome + "'");
		List<Trofeo> listTrofeo = queryTrofeo.getResultList();
		
		Iterator<Trofeo> trofIt = listTrofeo.iterator();
		
		if(trofIt.hasNext()){
			Trofeo trofeo = trofIt.next();
			
			Query queryUser = session.createQuery("FROM Utente user WHERE user.username='" + username + "'");
			List<Utente> listUser = queryUser.getResultList();
			
			Iterator<Utente> userIt = listUser.iterator();
			
			if(userIt.hasNext()){
				Utente user = userIt.next();
				user.getTrofeo().add(trofeo);
				
				session.saveOrUpdate(user);
			}

		}
		
		session.getTransaction().commit();
		
		session.close();
		
		
	}
}
