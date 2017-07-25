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

import gameplatform.db.table.Utente;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Livello;
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
			
			HttpSession session =  request.getSession();
			if (session.getAttribute("utenteGameplatform")==null){
				response.sendRedirect("login.op");
			} else {
				boolean perm = service.permControl((Utente)session.getAttribute("utenteGameplatform"), this.pageName);
				if (perm == true){

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
							
							// cancello le recensioni su tutti i giochi dell'utente
							Giocare play = new Giocare();
							List <Giocare> a=CRUD.executeQuery("FROM Giocare WHERE username='"+request.getParameter("id")+ "'");
							Iterator<Giocare> it1 = a.iterator();
							while(it1.hasNext()){
								play = (Giocare) it1.next();
								play.setUtente(null);
								CRUD.saveOrUpdate(play);	
							}
							
							// cancello i trofei conquistati dall'utente
							Livello liv = new Livello();
							List <Livello> b=CRUD.executeQuery("FROM Livello WHERE username='"+request.getParameter("id")+ "'");
							Iterator<Livello> it2 = b.iterator();
							while(it2.hasNext()){
								liv = (Livello) it2.next();
								liv.setUtente(null);
								CRUD.saveOrUpdate(liv);	
							}
							
							// cancello i trofei conquistati dall'utente INCOMPLETO
							Trofeo trof = new Trofeo();
							List <Trofeo> c=CRUD.executeQuery("FROM utente WHERE username='"+request.getParameter("id")+ "'");
							Iterator<Trofeo> it3 = c.iterator();
							
							while(it3.hasNext()){
								trof = (Trofeo) it3.next();
								trof.setUtente(null);
								CRUD.saveOrUpdate(trof);	
							}
							
						utente.setNome(id);
						CRUD.delete(utente);
					}
					}
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