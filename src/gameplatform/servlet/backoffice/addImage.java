package gameplatform.servlet.backoffice;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	public class addImage extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
    	private List<Gioco> x;
    	private boolean isMultipart;
    	private String filePath;
    	private int maxFileSize = 50 * 1024;
    	private int maxMemSize = 4 * 1024;
    	private File file ;
    	private String fileName;
    	
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addImage() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public void init(ServletConfig config) throws ServletException {
	    	
			// TODO Auto-generated method stub
			super.init(config);
	    	this.pageName = getInitParameter("pageName");
	    	this.template = service.templates(pageName);
	    	x = CRUD.executeQuery("SELECT nome FROM Gioco");
	        filePath = getServletContext().getInitParameter("file-upload"); 

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
			Gioco gioco = new Gioco();
			
			// Check that we have a file upload request
		      
		      java.io.PrintWriter out = response.getWriter( );
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      boolean isMultipart;
		      File file;
		      ServletContext app =getServletContext();
		      String path = app.getRealPath("");
		      long maxFileSize = 5000 * 1024;
		      int maxMemSize = 4 * 1024;
		      this.isMultipart = ServletFileUpload.isMultipartContent(request);
		      
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		   
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try { 
		          // Parse the request to get file items.
		          List fileItems = upload.parseRequest(request);
		 	
		          // Process the uploaded file items
		          Iterator i = fileItems.iterator();
		          while ( i.hasNext () ) {
		              FileItem fi = (FileItem)i.next();
		              if(fi.isFormField()){
		            	  String fieldname=fi.getFieldName();
		            	  if(fieldname.equals("gioco")){
		            		  gioco.setNome(fi.getString());
		            	  }
		              }

		              if ( !fi.isFormField () ) {
		            	
		                 // Write the file
		            	  
		                 if( fileName.lastIndexOf("\\") >= 0 ) {
		                    file = new File( path+filePath + this.fileName+".jpg") ;
		                 } else {
		                    file = new File( path+filePath + this.fileName+".jpg") ;
		                 }
		                 fi.write( file ) ;
		                 System.out.println("Uploaded Filename: " + this.fileName + "<br>");
		              }
		           }
		           } catch(Exception ex) {
		              System.out.println("ERRORE");
		           }
		
	        Immagine immagine = new Immagine();
			immagine.setPath(fileName);        
			immagine.setGioco(gioco);
			
			CRUD.saveOrUpdate(immagine);


			process(request, response);
		}
		
		
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("template", this.template);
			request.setAttribute("gioco", this.x);
	

			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}

	}

