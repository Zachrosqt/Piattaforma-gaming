package gameplatform.servlet;

import java.io.IOException;
import java.sql.Time;
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
import gameplatform.db.table.Utente;

/**
 * Servlet implementation class Zezzo
 */

public class ZezzoGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
	private Giocare gameplay;
	long startTime;
	long endTime;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZezzoGame() {
        super();
        // TODO Auto-generated constructor stub
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
			boolean perm = service.permControl((Utente)session.getAttribute("utenteGameplatform"), this.pageName);
			if (perm == true){
				process(request, response);
			} else {
				response.sendRedirect("accessdenied.op");
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String gioco = request.getParameter("gioco");
		String username = request.getParameter("user");
		boolean gioca = service.giocareAtGame(gioco, username);
		
		if(!gioca){
			service.startGiocare(gioco, username);
		}
		
		List<Giocare> listPlayed = service.playGame(gioco, username);
		
		Iterator<Giocare> listIt = listPlayed.iterator();
		
		if(listIt.hasNext()){
			Giocare play = listIt.next();
			play.setExp(play.getExp()+10);
			
			this.gameplay = play;
			
			service.updateGameplay(play);
		}
		
		
	}
	
	public void destroy() {
	
		endTime = System.nanoTime();
		long duration = (endTime - startTime);
		Time time = new Time(gameplay.getMinuti().getTime() + duration);
		
		gameplay.setMinuti(time);
		
		service.updateGameplay(gameplay);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}

}
