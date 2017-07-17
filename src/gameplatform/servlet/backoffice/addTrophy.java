package gameplatform.servlet.backoffice;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
import gameplatform.db.table.Trofeo;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.db.*;
import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
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

	@MultipartConfig
	public class addTrophy extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Gioco> h;
		private int lollo;
		private String temp;
		private static final String UPLOAD_DIRECTORY = "upload";
    	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    	//private List<Permesso> f;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addTrophy() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public void init(ServletConfig config) throws ServletException {
	    	
			// TODO Auto-generated method stub
			super.init(config);
	    	this.pageName = getInitParameter("pageName");
	    	this.template = service.templates(pageName); 
			h = CRUD.executeQuery("SELECT nome FROM Gioco");

			
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
			Gioco gioco = new Gioco();
			Trofeo trophy= new Trofeo();

			if (!ServletFileUpload.isMultipartContent(request)) {

			    PrintWriter writer = response.getWriter();
			    writer.println("Request does not contain upload data");
			    writer.flush();
			    return;
			}
			// configures upload settings
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			 
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			// constructs the directory path to store upload file
			String uploadPath = "/Users/mattiacaputo/Documents/workspaceAggiornato1/gameplatform/WebContent/assets/images"
				    + File.separator;
			// creates the directory if it does not exist
			
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
			    uploadDir.mkdir(); //
			}
			
			List formItems = null;
			try {
				formItems = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Iterator iter = formItems.iterator();
			 
			// iterates over form's fields
			while (iter.hasNext()) {
			    FileItem item = (FileItem) iter.next();
			    if(item.getFieldName().equals("gioco")){
	                String fieldvalue = item.getString();
	                gioco.setNome(fieldvalue);
			    }
			    if(item.getFieldName().equals("nome")){
	                String fieldvalue = item.getString();
	                trophy.setNome(fieldvalue);
			    }
			    if(item.getFieldName().equals("obiettivo")){
	                String fieldvalue = item.getString();
	                trophy.setObiettivo(fieldvalue);
			    }

			    // processes only fields that are not form fields
			    if (!item.isFormField()) {
			    	this.temp =item.getName();
			        String fileName = new File(item.getName()).getName();

			        String filePath = uploadPath + File.separator + fileName;
			        File storeFile = new File(filePath);
			 
			        // saves the file on disk
			        try {
						item.write(storeFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
			    }
			}
				
			
			trophy.setIcona(this.temp);
			trophy.setGioco(gioco);
			CRUD.saveOrUpdate(trophy);
		
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("gioco1", this.h);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}
	

