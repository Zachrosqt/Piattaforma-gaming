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
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Template;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


	public class editPlay extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private Gioco gioco= new Gioco();
		private List<Gioco> y;
		private List<Categoria> x;

	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public editPlay() {
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
					this.y = CRUD.executeQuery("FROM Gioco WHERE nome ='"+request.getParameter("id")+"'");
					this.x = CRUD.executeQuery("SELECT categoria FROM Categoria");
					this.gioco.setNome(request.getParameter("id"));
					
					List<Gioco> w;
					w= CRUD.executeQuery("SELECT categoria.categoria FROM Gioco WHERE nome ='"+request.getParameter("id")+"'");
					
					Categoria categoria = new Categoria();
					if (!w.isEmpty()){ // categoria is null, è stata cancellata
						System.out.println(w);
					} else{
					Object[] a = w.toArray();
					categoria.setCategoria(a[0].toString());}
					
					this.gioco.setCategoria(categoria);
					this.gioco.setDescrizione("");
					this.gioco.setSpecifiche("");
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
			System.out.println(request.getParameter("categoria"));

			Categoria categoria = new Categoria();
			categoria.setCategoria(request.getParameter("categoria"));
			this.gioco.setCategoria(categoria);
			this.gioco.setDescrizione(request.getParameter("testo"));
			this.gioco.setSpecifiche(request.getParameter("testo1"));
			this.gioco.setNome(request.getParameter("name"));
			CRUD.saveOrUpdate(this.gioco);
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("gioco", this.y);
			request.setAttribute("categoria", this.x);
			
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}