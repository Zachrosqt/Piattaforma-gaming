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
import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Template;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


	public class showTrophy extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Trofeo> sl;
		private List<Gioco> sh;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public showTrophy() {
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

					if(request.getParameter("id")!=null && !request.getParameter("del").equals("0")){

						List<Trofeo> listTrof = CRUD.executeQuery("FROM Trofeo WHERE id ='"+request.getParameter("id")+"'");

						Iterator<Trofeo> trofeoIt = listTrof.iterator();
						
						if (trofeoIt.hasNext()){

							Trofeo trofeo=trofeoIt.next();
							
							List<?> listJoin = CRUD.executeQuery("FROM Trofeo trof JOIN trof.utente WHERE trof.id ='"+request.getParameter("id")+"'");
							
							if(listJoin.size()>0){
								request.setAttribute("error", true);
								process(request, response);
							} else {
								CRUD.delete(trofeo);
								response.sendRedirect("showTrophy.op");
							}
						}
						
					} else {
						request.setAttribute("error", false);
						process(request, response);
					}
					
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
			

	    	this.sl = CRUD.executeQuery("FROM Trofeo");
			request.setAttribute("template", this.template);
			request.setAttribute("trofeo", this.sl);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}