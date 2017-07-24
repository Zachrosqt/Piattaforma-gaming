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
import javax.servlet.http.HttpSession;

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
			
			HttpSession session =  request.getSession();
			if (session.getAttribute("utenteGameplatform")==null){
				response.sendRedirect("login.op");
			} else {
				boolean perm = service.permControl((Utente)session.getAttribute("utenteGameplatform"), this.pageName);
				if (perm == true){

					request.setAttribute("test", false);
					
					if (request.getParameter("del")!= null && request.getParameter("del").equals("1")){
						
						Configuration conf = new Configuration().configure();
						Session sessionHib = conf.buildSessionFactory().getCurrentSession();
						sessionHib.beginTransaction();
						
						Query queryGroup = sessionHib.createQuery("FROM Gruppo group WHERE group.nome='" + request.getParameter("g") + "'");
						List<Gruppo> group = queryGroup.getResultList();
						
						Iterator<Gruppo> groupInt = group.iterator();

				    	if(groupInt.hasNext()){

				    		Gruppo groupTemp = groupInt.next();
				    		
				    		Query queryPerm = sessionHib.createQuery("FROM Permesso perm WHERE perm.nome='" + request.getParameter("p") + "'");
							List<Permesso> permesso = queryPerm.getResultList();
							
							Iterator<Permesso> permessoIt = permesso.iterator();
							
							if(permessoIt.hasNext()){
								Permesso permTemp = permessoIt.next();
								groupTemp.getPermesso().remove(permTemp);
					    		
					    		sessionHib.saveOrUpdate(groupTemp);
							}
				    		
				    	}
						
				    	sessionHib.getTransaction().commit();
						
				    	sessionHib.close();
				    	
				    	response.sendRedirect("showGroupPermission.op");
						
					} else {
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
			
			Configuration conf = new Configuration().configure();
			Session session = conf.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query queryGroup = session.createQuery("FROM Gruppo group WHERE group.nome='" + request.getParameter("nome") + "'");
			List<Gruppo> group = queryGroup.getResultList();
			
			Iterator<Gruppo> groupInt = group.iterator();

	    	if(groupInt.hasNext()){

	    		Gruppo groupTemp = groupInt.next();
	    		
	    		
	    		Set<Permesso> setPermesso = groupTemp.getPermesso();
	    		List<Permesso> permessi = new ArrayList<Permesso>();
	    		
	    		for(Permesso tempPer: setPermesso)
	    			permessi.add(tempPer);
	    		
	    		request.setAttribute("groupToControl", groupTemp);
	    		request.setAttribute("perm", permessi);
	    		
	    		
	    	}
			
			session.getTransaction().commit();
			
			session.close();
			
			request.setAttribute("test", true);
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			this.fc = CRUD.executeQuery("FROM Gruppo");
			
			request.setAttribute("template", this.template);
			request.setAttribute("gruppo", this.fc);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}