package gameplatform.servlet.backoffice;
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
import gameplatform.db.table.Trofeo;
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

	@MultipartConfig
	public class addTrophy extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private String pageName;
		private List<Template> template;
		private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		private List<Gioco> h;
		private boolean isMultipart;
    	private int maxFileSize = 5000 * 1024;
    	private int maxMemSize = 4 * 1024;

	       
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
			
			String filePath =  "assets\\images\\trofeo\\";
			this.isMultipart = ServletFileUpload.isMultipartContent(request);

			if( !isMultipart ) {
		         return;
		      }
		      DiskFileItemFactory factory = new DiskFileItemFactory();

		      ServletContext app =getServletContext();
		      String path = app.getRealPath("");
		      
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		   
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try { 
		    	  
		    	  Trofeo trofeo = new Trofeo();
		    	  
		          // Parse the request to get file items.
		          List fileItems = upload.parseRequest(request);
		 	
		          // Process the uploaded file items
		          Iterator i = fileItems.iterator();
		          while ( i.hasNext () ) {
		              FileItem fi = (FileItem)i.next();
		              if(fi.isFormField()){
		            	  String fieldname=fi.getFieldName();
		            	  if(fieldname.equals("gioco")){
		            		  List<Gioco> game= CRUD.executeQuery("FROM Gioco game WHERE game.nome = '" + fi.getString() + "'");
		            		  Iterator<Gioco> gameIt = game.iterator();
		            		  if (gameIt.hasNext()){
		            			  trofeo.setGioco(gameIt.next());
		            		  }
		            	  }
		            	  if(fieldname.equals("obiettivo")){
		            		  trofeo.setObiettivo(fi.getString());
		            	  }
		            	  if(fieldname.equals("nome")){
		            		  trofeo.setNome(fi.getString());
		            	  }
		              }

		              if ( !fi.isFormField () ) {
		            	
		            	 File file ;
		            	 String fileName = fi.getName();
			             String contentType = fi.getContentType();
			             long sizeInBytes = fi.getSize();
			            
			               // Write the file
			               
			               
			             if((contentType.equals("image/jpeg") || contentType.equals("image/png")) && sizeInBytes <= maxFileSize){
		            	  
			                 if( fileName.lastIndexOf("\\") >= 0 ) {
			                    file = new File( path + filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
			                 } else {
			                    file = new File( path + filePath + fileName.substring( fileName.lastIndexOf("\\")+1)) ;
			                 }
			                 fi.write( file ) ;
			                 
			                 trofeo.setIcona(fileName);
			             }
		              }		
		           }
		          
		          CRUD.saveOrUpdate(trofeo);
		       } catch(Exception ex) {
		            System.out.println(ex);
		       }
			
		
			process(request, response);
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			h = CRUD.executeQuery("FROM Gioco");
			
			request.setAttribute("template", this.template);
			request.setAttribute("gioco", this.h);
			
			RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
			view.forward(request, response);
		}
		
		
	}
	

