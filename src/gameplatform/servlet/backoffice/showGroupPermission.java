package gameplatform.servlet.backoffice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import gameplatform.db.table.Permesso;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


	public class showGroupPermission extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Permesso> fv;
		private List<Gruppo> fc;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public showGroupPermission() {
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
			
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			Configuration conf = new Configuration().configure();
			Session session = conf.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query queryPerm = session.createQuery("FROM Permesso");
			List<Permesso> perm = queryPerm.getResultList();
			
			Iterator<Permesso> permInt = perm.iterator();

	    	while(permInt.hasNext()){
	    		String text = "";

	    		Permesso permTemp = permInt.next();
	    		
	    		text += "Permesso " + permTemp.getNome() + " associato ai gruppi: ";
	    		
	    		Set<Gruppo> setGroup = permTemp.getGruppo();
	    		
	    		System.out.println(setGroup.size());
	    		
	    		
	    		for(Gruppo group : setGroup)
	    			text += group.getNome() + ", ";
	    		
	    		
	    		System.out.println(text);
	    	}
			
			session.getTransaction().commit();
			
			session.close();
			
			request.setAttribute("template", this.template);
			request.setAttribute("permesso", this.fv);
			request.setAttribute("gruppo", this.fc);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}