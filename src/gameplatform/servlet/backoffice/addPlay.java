package gameplatform.servlet.backoffice;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import gameplatform.db.table.Permesso;
import gameplatform.db.table.PermessoTemplate;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.db.*;


	public class addPlay extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
    	private List<Categoria> x;
    	private String returnDate;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addPlay() {
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
			// TODO Auto-generated method stub
			Gioco gioco = new Gioco();
			
			Categoria categoria = new Categoria();
			
			categoria.setCategoria(request.getParameter("categoria"));
			

			gioco.setCategoria(categoria);
			gioco.setDescrizione(request.getParameter("testo"));
			gioco.setNome(request.getParameter("name"));
			gioco.setSpecifiche(request.getParameter("testo1"));	
	        Calendar cal=Calendar.getInstance();	        
			gioco.setDate(cal);
			
	
			CRUD.saveOrUpdate(gioco);
			
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			x = CRUD.executeQuery("SELECT categoria FROM Categoria");
			
			request.setAttribute("template", this.template);
			request.setAttribute("categoria", this.x);

			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}

	}
