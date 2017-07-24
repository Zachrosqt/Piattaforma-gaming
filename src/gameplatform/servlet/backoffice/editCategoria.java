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
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


public class editCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private String v;
	private Categoria category=new Categoria();
	private List<Categoria> cat;
	private List<Gioco> gioc;



       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCategoria() {
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
		this.gioc= CRUD.executeQuery("FROM Gioco WHERE categoria.categoria ='"+request.getParameter("id")+"'");
		this.cat= CRUD.executeQuery("FROM Categoria WHERE categoria  ='"+request.getParameter("id")+"'");
		Iterator<Categoria> userIt = this.cat.iterator();
		while(userIt.hasNext()){
			this.category= userIt.next();
			
		}

		//mettiamo le categorie a null
		Iterator<Gioco> giocoIt = this.gioc.iterator();
			while(giocoIt.hasNext()){
				Gioco game= giocoIt.next();	
				game.setCategoria(null);
				CRUD.saveOrUpdate(game);		
		}
			System.out.println(request.getParameter("name"));
			this.category.setCategoria(request.getParameter("name"));
			CRUD.saveOrUpdate(category);
			
			
			Iterator<Gioco> gioco = this.gioc.iterator();
			while(gioco.hasNext()){
				Gioco game= gioco.next();	
				game.setCategoria(this.category);
				CRUD.saveOrUpdate(game);		
		}

				process(request, response);
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		request.setAttribute("categoria", this.v);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}
	
	
}