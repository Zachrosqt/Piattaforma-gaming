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
	private boolean kp;
	private List<Utente> la;
	

       
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

		process(request, response);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.ju = CRUD.executeQuery("FROM Utente WHERE username ='"+request.getParameter("id")+"'");
		
		Iterator<Utente> userIt = this.ju.iterator();
		
		if(userIt.hasNext()){
			Utente temp = userIt.next();
			
			temp.setNome(request.getParameter("nome"));
			temp.setUsername(request.getParameter("username"));
			temp.setCognome(request.getParameter("surname"));
			
			this.ko = Integer.parseInt(request.getParameter("eta"));
			temp.setEta(ko);	
			temp.setEmail(request.getParameter("email"));
		    this.kk =Integer.parseInt(request.getParameter("exp"));
		    temp.setExp_tot(kk);	    
		    temp.setPassword(request.getParameter("password"));
		    
		    if (request.getParameter("ban").equals("1")) 
		    	kp =true;
		    else 
		    	kp =false;
		    temp.setBan(kp);
		    
		    List<Gruppo> listGroup = CRUD.executeQuery("FROM Gruppo WHERE nome ='"+request.getParameter("gruppo")+"'");
		    
		    Iterator<Gruppo> groupIt = listGroup.iterator();
		    
		    if(groupIt.hasNext()){
		    	Gruppo tempGroup = groupIt.next();
		    	
		    	temp.setGruppo(tempGroup);
		    }
		    
		    CRUD.saveOrUpdate(temp);
			
		}

		
				process(request, response);
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.ju = CRUD.executeQuery("FROM Utente WHERE username ='"+request.getParameter("id")+"'");
		this.la = CRUD.executeQuery("SELECT nome FROM Gruppo");
		
		request.setAttribute("template", this.template);
		request.setAttribute("utente", this.ju);
		request.setAttribute("utente1", this.la);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}
	
	
}