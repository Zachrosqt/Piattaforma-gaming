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

import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Permesso;
import gameplatform.db.table.PermessoTemplate;
import gameplatform.db.table.Template;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.db.*;


	public class addGroupPermission extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
    	private List<Gruppo> x;
    	private List<Permesso> y;
    	private List<Permesso> z;



	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addGroupPermission() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public void init(ServletConfig config) throws ServletException {
	    	
			// TODO Auto-generated method stub
			super.init(config);

	    	this.pageName = getInitParameter("pageName");
	    	this.template = service.templates(pageName);

	    	x = CRUD.executeQuery("SELECT nome FROM Gruppo");
	    	y = CRUD.executeQuery("SELECT nome FROM Permesso");
	    	z = CRUD.executeQuery("SELECT indirizzo FROM Permesso");
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
			Gruppo gruppo = new Gruppo();
			Permesso permesso = new Permesso();
			
			
			gruppo.setNome(request.getParameter("nome"));
			permesso.setNome(request.getParameter("servizio"));
			System.out.println(permesso.getNome());
			System.out.println(gruppo.getNome());
			permesso.setIndirizzo(request.getParameter("servizio") + ".op");
			
			CRUD.saveOrUpdate(permesso);
			
			//permesso.getGruppo().add(gruppo); //join
			gruppo.getPermesso().add(permesso);
			CRUD.saveOrUpdate(gruppo);
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("gruppo", this.x);
			request.setAttribute("pippo", this.z);
			request.setAttribute("servizio", this.y);

			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}

	}


