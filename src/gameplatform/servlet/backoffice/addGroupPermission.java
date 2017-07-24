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

import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Permesso;
import gameplatform.db.table.PermessoTemplate;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;
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
			
			Configuration conf = new Configuration().configure();
			Session session = conf.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query queryGroup = session.createQuery("FROM Gruppo group WHERE group.nome='" + request.getParameter("nome")  + "'");
			List<Gruppo> groupList = queryGroup.getResultList();
			
			Iterator<Gruppo> groupIt = groupList.iterator();
			
			if(groupIt.hasNext()){
				Gruppo gruppo = groupIt.next();
				String[] perm = request.getParameterValues("permessi");
				
				for(String temp: perm){
					
					Query queryPerm = session.createQuery("FROM Permesso perm WHERE perm.nome='" + temp + "'");
					List<Permesso> permList = queryPerm.getResultList();
					
					Iterator<Permesso> permIt = permList.iterator();
					
					if(permIt.hasNext()){

						Permesso permesso = permIt.next();
						
						gruppo.getPermesso().add(permesso);
					}
					
				}
				
				session.saveOrUpdate(gruppo);
			}
			
			session.getTransaction().commit();
			
			session.close();
			
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			x = CRUD.executeQuery("FROM Gruppo");
	    	y = CRUD.executeQuery("FROM Permesso");
			
			request.setAttribute("template", this.template);
			request.setAttribute("gruppo", this.x);
			request.setAttribute("servizio", this.y);

			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}

	}


