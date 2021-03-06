package gameplatform.servlet.backoffice;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Permesso;
import gameplatform.db.table.PermessoTemplate;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.db.*;
import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import java.io.*;
 import java.sql.*;
 import java.util.*;
 import java.util.regex.*;
 import javax.servlet.ServletException;
 import javax.servlet.http.*;
 import org.apache.commons.fileupload.*;
 import org.apache.commons.fileupload.disk.DiskFileItemFactory;
 import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@MultipartConfig
public class showRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	
	private List<Giocare> x;

      
	
    public showRecensione() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("pageName");
    	this.template = service.templates(pageName);
    	this.x = CRUD.executeQuery("SELECT recensione, pk FROM Giocare WHERE approvato ="+"0");
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
				
				this.x = CRUD.executeQuery("SELECT recensione, pk FROM Giocare WHERE approvato ="+"0"+" AND recensione!=''");

				if(request.getParameter("del")!=null){

					if(request.getParameter("del").equals("0")){ //approve
						String gioco = request.getParameter("nome");
						List<Giocare> y;
						Giocare giocare= new Giocare();
						y = CRUD.executeQuery("FROM Giocare WHERE pk.gioco.nome='"+request.getParameter("nome")+"'" +" AND pk.utente.username ='"+request.getParameter("username")+"'");
						
						Iterator<Giocare> it = y.iterator();
						while (it.hasNext()) {
							giocare = (Giocare) it.next();
				        }
						giocare.setApprovato(true);  
						CRUD.saveOrUpdate(giocare);
						
						List<Giocare> mediaGioco = CRUD.executeQuery("FROM Giocare gioca WHERE gioca.pk.gioco.nome='" + gioco + "' AND gioca.approvato = '1'");
						
						Iterator<Giocare> medIt = mediaGioco.iterator();
						
						int somma=0;
						
						while(medIt.hasNext()){
							Giocare play = medIt.next();
							somma += play.getVoto();
						}
						
						int media=somma/mediaGioco.size();
						
						List<Gioco> game = CRUD.executeQuery("FROM Gioco game WHERE game.nome='" + gioco + "'");
						
						Iterator<Gioco> gameIt = game.iterator();
						
						if(gameIt.hasNext()){
							Gioco gameMed = gameIt.next();
							gameMed.setMediaGioco(media);
							
							CRUD.saveOrUpdate(gameMed);
							
						}
					}
						
						
					
					if(request.getParameter("del").equals("1")){
						String gioco = request.getParameter("nome");
						List<Giocare> y;
						Giocare giocare= new Giocare();
						y = CRUD.executeQuery("FROM Giocare WHERE pk.gioco.nome='"+request.getParameter("nome")+"'"+"and pk.utente.username ='"+request.getParameter("username")+"'");
						Iterator<Giocare> it = y.iterator();
						while (it.hasNext()) {
							giocare = (Giocare) it.next();
				        }
						giocare.setRecensione("");
						giocare.setApprovato(false);
						CRUD.saveOrUpdate(giocare);
						
						List<Giocare> mediaGioco = CRUD.executeQuery("FROM Giocare gioca WHERE gioca.pk.gioco.nome='" + gioco + "' AND gioca.approvato = '1'");
						
						Iterator<Giocare> medIt = mediaGioco.iterator();
						
						int somma=0;
						
						while(medIt.hasNext()){
							Giocare play = medIt.next();
							somma += play.getVoto();
						}
						
						int media=somma/mediaGioco.size();
						
						List<Gioco> game = CRUD.executeQuery("FROM Gioco game WHERE game.nome='" + gioco + "'");
						
						Iterator<Gioco> gameIt = game.iterator();
						
						if(gameIt.hasNext()){
							Gioco gameMed = gameIt.next();
							gameMed.setMediaGioco(media);
							
							CRUD.saveOrUpdate(gameMed);
							
						}
					}
					
					response.sendRedirect("showRecensione.op");
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
		process(request, response);
	}
	
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		request.setAttribute("path", this.x);



		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}

}