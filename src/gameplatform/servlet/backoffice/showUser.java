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

import gameplatform.db.table.Utente;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.db.table.Trofeo;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


	public class showUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Utente> ss;
		

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public showUser() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public void init(ServletConfig config) throws ServletException {
	    	
			// TODO Auto-generated method stub
			super.init(config);
	    	this.pageName = getInitParameter("pageName");
	    	this.template = service.templates(pageName); 
	    	this.ss = CRUD.executeQuery("SELECT username, nome, cognome, email FROM Utente");
	    	
			
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub 
	    	this.ss = CRUD.executeQuery("SELECT username, nome, cognome, email FROM Utente");

			if(request.getParameter("id")!=null){
				if(!request.getParameter("del").equals("0")){
					List<Utente> ss;
					Utente utente= new Utente();
					
					System.out.print(request.getParameter("id"));

					ss = CRUD.executeQuery("FROM Utente WHERE id ='"+request.getParameter("id")+"'");
					System.out.print(request.getParameter("id"));

				String id =(request.getParameter("id"));
					Iterator<Utente> it = ss.iterator();
					while(it.hasNext()){
						utente = (Utente) it.next();
					}
					
				utente.setNome(id);
				CRUD.delete(utente);
			}
			}
			process(request, response);
			
			}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("utente", this.ss);
			//request.setAttribute("pippo", this.si);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}