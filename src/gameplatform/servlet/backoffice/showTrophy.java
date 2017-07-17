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
	    	sl = CRUD.executeQuery("SELECT  nome, obiettivo , gioco FROM Trofeo");
	    	//sh = CRUD.executeQuery("SELECT nome FROM Gioco");

	    	
			
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub 

			if(request.getParameter("id")!=null){
				if(!request.getParameter("del").equals("0")){
					List<Trofeo> sl;
					Trofeo trofeo= new Trofeo();
					
					System.out.print(request.getParameter("id"));

					sl = CRUD.executeQuery("FROM Trofeo WHERE id ='"+request.getParameter("id")+"'");
					System.out.print(request.getParameter("id"));

				String id =(request.getParameter("id"));
					Iterator<Trofeo> it = sl.iterator();
					while(it.hasNext()){
						trofeo = (Trofeo) it.next();
					}
					
				trofeo.setNome(id);
				CRUD.delete(trofeo);
			}
			}
			process(request, response);
			
			}
		

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			Trofeo trophy= new Trofeo();
			Gioco gioco = new Gioco();
			gioco.setNome(request.getParameter("gioco"));
			trophy.setGioco(gioco);
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("trofeo", this.sl);
			//request.setAttribute("gioco", this.sh);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}