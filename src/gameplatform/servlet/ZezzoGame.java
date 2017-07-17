package gameplatform.servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Utente;

/**
 * Servlet implementation class Zezzo
 */

public class ZezzoGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
	private Giocare gameplay = new Giocare();
	long startTime;
	long endTime;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZezzoGame() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("gioco");
    	
    	startTime = System.nanoTime();
    
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =  request.getSession();
		if (session.getAttribute("utenteGameplatform")==null){
			response.sendRedirect("login.op");
		} else {
			process(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String gioco = this.pageName;
		String username = request.getParameter("user");
		boolean gioca = service.giocareAtGame(gioco, username);
		int globalLv = 0;
		
		String text=", , ,";
		
		if(!gioca){
			service.startGiocare(gioco, username);
		}
		
		String btn = request.getParameter("button");
		
		if(btn != null && !btn.equals("")){
			if(btn.equals("exp")){
				List<Giocare> listPlayed = service.playGame(gioco, username);
					
				Iterator<Giocare> listIt = listPlayed.iterator();
					
				if(listIt.hasNext()){
					Giocare play = listIt.next();
					play.setExp(play.getExp()+10);
						
					play.setNumAccessi(this.gameplay.getNumAccessi());
					this.gameplay = play;
					
					service.updateGameplay(play);
				}
				
				int currentLv = (this.gameplay.getLivello()*100) + 100;
				
				if(this.gameplay.getExp() >= currentLv){
					
					int curLv = (int) (this.gameplay.getExp()/100);
					this.gameplay.setLivello(curLv);
						
					service.updateGameplay(this.gameplay);
	
				}
				
				List<Livello> listLivello = service.livelliList(username);
				
				Livello lv = listLivello.get(listLivello.size() - 1);
				
				globalLv = lv.getLivello();
				
				int curGlobalLv = (lv.getLivello()*100*service.allGames().size()) + (100*service.allGames().size());
				
				if(service.sumUserExp(username) >= curGlobalLv){
					
					List<Utente> listCurrentUser = service.username(username);
					
					Iterator<Utente> userIt = listCurrentUser.iterator();
					
					if(userIt.hasNext()){
						Utente currUser = userIt.next();
						Livello newLv = new Livello();
						int allExp = service.sumUserExp(username);
						int numGames = service.allGames().size();
						int curLv = allExp/(100 * numGames);
						
						globalLv = curLv;
						newLv.setDate(Calendar.getInstance());
						newLv.setLivello(curLv);
						newLv.setUtente(currUser);
						
						service.addLv(newLv);
					}
				}
				
				text=" " + this.gameplay.getLivello() + ", " + this.gameplay.getExp() + ", " + globalLv + ", " + this.gameplay.getNumAccessi();
				
			} else {
				endTime = System.nanoTime();
				long duration = (endTime - startTime);
				Time time = new Time(gameplay.getMinuti().getTime() + duration);
				
				gameplay.setMinuti(time);
				
				service.updateGameplay(gameplay);
			}
		}
		
	    
	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);
		
		
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String game = this.pageName;
		String user = request.getParameter("username");
		boolean gioca = service.giocareAtGame(game, user);

		if(!gioca){
			this.gameplay.setNumAccessi(1);
		} else {
			List<Giocare> listGames = service.playGame(game, user);
			
			Iterator<Giocare> listGameIt = listGames.iterator();
				
			if(listGameIt.hasNext()){
				Giocare play = listGameIt.next();
					
				this.gameplay.setNumAccessi(play.getNumAccessi() + 1);
				
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/zezzo.jsp");
		view.forward(request, response);
	}

}
