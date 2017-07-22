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


	public class showCategoria extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Categoria> v;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public showCategoria() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public void init(ServletConfig config) throws ServletException {
	    	
			// TODO Auto-generated method stub
			super.init(config);
	    	this.pageName = getInitParameter("pageName");
	    	this.template = service.templates(pageName); 
	    	this.v = CRUD.executeQuery("SELECT categoria FROM Categoria");

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

					this.v = CRUD.executeQuery("SELECT categoria FROM Categoria");
					
					if(request.getParameter("id")!=null){
						if(!request.getParameter("del").equals("0")){
							List<Categoria> v;
							Categoria categoria= new Categoria();
							

							v = CRUD.executeQuery("FROM Categoria WHERE categoria ='" +request.getParameter("id")+ "'");
							String id = (request.getParameter("id"));
							Iterator<Categoria> it = v.iterator();
							while(it.hasNext()){
								categoria = (Categoria) it.next();
							}
						
							categoria.setCategoria(id);
						CRUD.delete(categoria);
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
			request.setAttribute("categoria", this.v);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}