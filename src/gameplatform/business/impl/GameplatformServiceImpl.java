package gameplatform.business.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.db.table.Categoria;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Livello;
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
	public boolean registration(Utente user, Livello lv) {
		
		try{
			
			crud.saveOrUpdate(user);
			crud.saveOrUpdate(lv);
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
	public List<Categoria> allCategorie() {
		List<Categoria> categoria = crud.executeQuery("FROM Categoria categoria");
		
		return categoria;
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
	
	public List<Giocare> allReview(String game){
		List<Giocare> recensioni = crud.executeQuery("FROM Giocare game WHERE game.pk.gioco.nome = '" + game + "'");
		return recensioni;
	}

	@Override
	public List<Utente> username(String username) {
		
		List<Utente> user = crud.executeQuery("FROM Utente user WHERE user.username='" + username + "'");
		return user;
	}

	@Override
	public List<Utente> userEmail(String email) {
		// TODO Auto-generated method stub
		
		List<Utente> mail = crud.executeQuery("FROM Utente user WHERE user.email='" + email + "'");
		return mail;
	}

	@Override
	public List<Gruppo> group(String group) {
		// TODO Auto-generated method stub
		
		List<Gruppo> g = crud.executeQuery("FROM Gruppo groups WHERE groups.nome='" + group + "'");
		return g;
	}

	@Override
	public String livelli(String usarname) {
		// TODO Auto-generated method stub
		
		List<Livello> lv = crud.executeQuery("FROM Livello lv WHERE lv.utente.username='" + usarname + "' ORDER BY lv.livello");
		
		Iterator<Livello> it = lv.iterator();
		String arrayScript= "[";
		while(it.hasNext()){
			Livello temp = it.next();
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			String formatted = format1.format(temp.getDate().getTime());
			arrayScript += "['" + temp.getLivello() + "', '" + formatted + "']";
			if(it.hasNext()){
				arrayScript += ",";
			}
		}
		arrayScript += "]";
		return arrayScript;
	}

	@Override
	public int giocare(String usarname) {
		// TODO Auto-generated method stub
		
		List<Giocare> giocare = crud.executeQuery("FROM Giocare gioca WHERE gioca.pk.utente.username='" + usarname + "'");
		
		return giocare.size();
	}

	@Override
	public boolean insertReview(String review, int voto, String gioco, String username) {
		// TODO Auto-generated method stub
		
		List<Giocare> giocare = crud.executeQuery("FROM Giocare gioca WHERE gioca.pk.utente.username='" + username + "' AND gioca.pk.gioco.nome='" + gioco + "'");
		
		Iterator<Giocare> it = giocare.iterator();
		
		if(it.hasNext()){
			Giocare gioc = it.next();
			gioc.setRecensione(review);
			gioc.setVoto(voto);
			gioc.setData(Calendar.getInstance());
			
			crud.saveOrUpdate(gioc);
			
			List<Giocare> mediaGioco = crud.executeQuery("FROM Giocare gioca WHERE gioca.pk.gioco.nome='" + gioco + "'");
			
			Iterator<Giocare> medIt = mediaGioco.iterator();
			
			int somma=0;
			
			while(medIt.hasNext()){
				Giocare play = medIt.next();
				somma += play.getVoto();
			}
			
			int media=somma/giocare.size();
			
			List<Gioco> game = crud.executeQuery("FROM Gioco game WHERE game.nome='" + gioco + "'");
			
			Iterator<Gioco> gameIt = game.iterator();
			
			if(gameIt.hasNext()){
				Gioco gameMed = gameIt.next();
				gameMed.setMediaGioco(media);
				
				crud.saveOrUpdate(gameMed);
				
				return true;
			}
			
		}
		
		return false;
		
	}

	@Override
	public void insertUser(Utente user) {
		// TODO Auto-generated method stub
		crud.saveOrUpdate(user);
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
		Time time = new Time(0);
		startGioco.setMinuti(time);
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

}
