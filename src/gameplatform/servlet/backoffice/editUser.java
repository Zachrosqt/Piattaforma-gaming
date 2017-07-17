package gameplatform.servlet.backoffice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private List<Utente> ju;
	private int ko;
	private int kk;
	private int ku;
	private boolean kp;
	private List<Utente> la;
	private List<Utente> uu;
	private Utente user =new Utente();


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editUser() {
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
		this.ju = CRUD.executeQuery("FROM Utente WHERE username ='"+request.getParameter("id")+"'");
		this.la = CRUD.executeQuery("SELECT nome FROM Gruppo");
		this.uu = CRUD.executeQuery("SELECT gruppo FROM Utente where username ='"+request.getParameter("id")+"'");
		Object[] w = ju.toArray();
		String s = w[0].toString();
		this.user.setUsername(s);
		CRUD.delete(s);
		process(request, response);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.user.setNome(request.getParameter("nome"));
		this.user.setUsername(request.getParameter("username"));
		this.user.setCognome(request.getParameter("surname"));
	   

		this.ko = Integer.parseInt(request.getParameter("eta"));
	    this.user.setEta(ko);	
	    this.user.setEmail(request.getParameter("email"));
	    this.kk =Integer.parseInt(request.getParameter("exp"));
	    this.user.setExp_tot(kk);	    
	    this.user.setPassword(request.getParameter("password"));
	    this.ku =Integer.parseInt(request.getParameter("numeroaccessi"));
	    this.user.setNumeroAccessi(ku);
	    kp =Boolean.parseBoolean(request.getParameter("ban"));
	    this.user.setBan(kp);
	    Gruppo gruppo = new Gruppo();
		gruppo.setNome(request.getParameter("gruppo"));
		this.user.setGruppo(gruppo);
	    
	    
	    
		CRUD.saveOrUpdate(user);
				process(request, response);
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		request.setAttribute("utente", this.ju);
		request.setAttribute("utente1", this.la);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}
	
	
}