package gameplatform.servlet;

import java.io.IOException;
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
import gameplatform.business.impl.UtenteGiocare;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

/**
 * Servlet implementation class GamePage
 */

public class GamePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GamePage() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("pageName");
    	
    	this.template = service.templates(pageName);
		
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
		
		boolean  test= service.insertReview(request.getParameter("messagereview"), Integer.parseInt(request.getParameter("review-rate")), request.getParameter("game-review"), request.getParameter("user-review"));
		
		String text = "";
		
		if(!test){
			text = "false";
		} else {
			text = "true";
		}

	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("allreview", service.allReview(request.getParameter("id")));
		request.setAttribute("review", service.recensioni(request.getParameter("id")));
		request.setAttribute("gallery", service.allImmages(request.getParameter("id")));
		request.setAttribute("gioco", service.game(request.getParameter("id")));
		request.setAttribute("template", this.template);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}

}
