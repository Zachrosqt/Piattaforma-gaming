package gameplatform.servlet.backoffice;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import gameplatform.db.table.Giocare;
import gameplatform.db.table.GiocareId;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class editImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private List<Gioco> x;
	private List<Immagine> y;
	String filePath;


	
    public editImage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("pageName");
    	this.template = service.templates(pageName);
    	filePath =  "assets\\images\\games\\temp\\"; 
 
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
		
		List<Immagine> listImmagine = CRUD.executeQuery("FROM Immagine WHERE id="+request.getParameter("id"));
		
		Iterator<Immagine> itImg = listImmagine.iterator();
		
		if(itImg.hasNext()){
			System.out.println("Test");
			Immagine immagine = itImg.next();
			
			ServletContext app=getServletContext();
			String path=app.getRealPath("");
			File f=new File(path+"assets/images/games/" + immagine.getGioco().getNome() + "/" + immagine.getPath());
			
			Files.copy(f.toPath(), (new File(path + "assets/images/games/" + request.getParameter("gioco") + "/" + immagine.getPath())).toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			f.delete();
			
			List<Gioco> listGame = CRUD.executeQuery("FROM Gioco game WHERE game.nome ='" + request.getParameter("gioco") + "'");
			
			Gioco gioco = listGame.get(0);
			
			immagine.setGioco(gioco);
			
			CRUD.saveOrUpdate(immagine);
		}              
		               
	               
		response.sendRedirect("showImage.op");
	}
	
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.y = CRUD.executeQuery("FROM Immagine WHERE id="+request.getParameter("id"));
		this.x = CRUD.executeQuery("FROM Gioco");
		
		request.setAttribute("idImg", request.getParameter("id"));
		request.setAttribute("template", this.template);
		request.setAttribute("gioco", this.x);
		request.setAttribute("foto", this.y);



		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}

}