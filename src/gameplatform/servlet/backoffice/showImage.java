package gameplatform.servlet.backoffice;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
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
public class showImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private List<Immagine> x;

      
	
    public showImage() {
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
				
				if(request.getParameter("id")!=null && !request.getParameter("del").equals("0")){

					List<Immagine> y;
					Immagine immagine= new Immagine();
					y = CRUD.executeQuery("FROM Immagine WHERE id="+request.getParameter("id"));
					int id = Integer.parseInt(request.getParameter("id"));
					Iterator<Immagine> it = y.iterator();
					while (it.hasNext()) {
						immagine = it.next();
			        }
					
					ServletContext app=getServletContext();
					String path=app.getRealPath("");
					File f=new File(path+"assets/images/games/" + immagine.getGioco().getNome() + "/" + immagine.getPath());
					f.delete();
					  
					CRUD.delete(immagine);
					
					response.sendRedirect("showImage.op");
					
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
		
		x = CRUD.executeQuery("FROM Immagine");
		
		request.setAttribute("template", this.template);
		request.setAttribute("path", this.x);



		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}

}