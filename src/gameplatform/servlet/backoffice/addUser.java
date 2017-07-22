package gameplatform.servlet.backoffice;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.security.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;
import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Permesso;
import gameplatform.db.table.Template;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


	public class addUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Gruppo> l;
		private String y;
		private int x;
		private int pippo;
		private String ciccio;
    	//private List<Permesso> f;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addUser() {
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

			Utente user = new Utente();			 
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			
			y=request.getParameter("ban");
			if (y== "0"){
				user.setBan(true);
					
			}
			else {
				user.setBan(false);
			}
			
			user.setEmail(request.getParameter("email"));
			user.setExp_tot(0);
			user.setNumeroAccessi(0);


			pippo = Integer.parseInt(request.getParameter("eta"));
			user.setEta(pippo);			
			user.setUsername(request.getParameter("username"));
			//MD5 code
			String ciccio=(request.getParameter("password"));
			
			try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(ciccio.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			ciccio = hash.toString(16);
			} catch (NoSuchAlgorithmException nsae) {
			// ignore
			}
			user.setPassword(ciccio);
			
	
			 

			Gruppo gruppo = new Gruppo();
			gruppo.setNome(request.getParameter("gruppo"));
			user.setGruppo(gruppo);
			
			CRUD.saveOrUpdate(user);
			process(request, response);
		}
		
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			l = CRUD.executeQuery("SELECT nome FROM Gruppo");
			
			request.setAttribute("template", this.template);
			request.setAttribute("gruppo", this.l);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}
	

