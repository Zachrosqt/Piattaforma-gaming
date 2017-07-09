package gameplatform.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/RegistrationPage")
public class RegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationPage() {
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
			process(request, response);
		} else {
			response.sendRedirect("gameplatform.op");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String text = "";
		
		List<Utente> username = service.username(request.getParameter("username"));
		
		Iterator<Utente> usernameit = username.iterator();
		
		if (usernameit.hasNext()){

			text = "false username";
			
		} else {
			
			List<Utente> mail = service.userEmail(request.getParameter("mail"));
			
			Iterator<Utente> mailit = mail.iterator();
			if (mailit.hasNext()){
				text = "false mail";
				
			} else {
				
				List<Gruppo> group = service.group("User");
				
				Iterator<Gruppo> it = group.iterator();
				
				if (it.hasNext()){
					
					System.out.println("Test");
					
					Utente user = new Utente();
					user.setBan(false);
					user.setCognome(request.getParameter("cognome"));
					user.setEmail(request.getParameter("mail"));
					user.setEta(Integer.parseInt(request.getParameter("eta")));
					user.setExp_tot(0);
					
					Gruppo gruppo = it.next();
					
					user.setGruppo(gruppo);
					
					user.setNome(request.getParameter("nome"));
					user.setNumeroAccessi(0);
					user.setUsername(request.getParameter("username"));
					user.setPassword(request.getParameter("password"));
					
					Livello lv = new Livello();
					lv.setDate(Calendar.getInstance());
					lv.setLivello(0);
					lv.setUtente(user);
					
					user.getLivello().add(lv);
					
					boolean reg = service.registration(user, lv);
					
					if (!reg){
						text = "false";
					} else {
						text = "true";
					}
				} else {
					text = "false";
				}
				
			}
			
		}

	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}

}
