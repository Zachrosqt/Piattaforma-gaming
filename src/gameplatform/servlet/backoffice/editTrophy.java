package gameplatform.servlet.backoffice;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


public class editTrophy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private List<Trofeo> ju;
	private Trofeo trophy =new Trofeo();


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editTrophy() {
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
		int maxFileSize = 5000 * 1024;
    	int maxMemSize = 4 * 1024;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

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
	    	  
	    	  List<Trofeo> trofList= CRUD.executeQuery("FROM Trofeo trof WHERE trof.nome = '" + request.getParameter("id") + "'");
	    	  
	    	  Iterator<Trofeo> trofIt = trofList.iterator();
	    	  
	    	  if(trofIt.hasNext())
	    		  trofeo = trofIt.next();
	    	  
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
	            	  if(fieldname.equals("objective")){
	            		  trofeo.setObiettivo(fi.getString());
	            	  }
	              }

	              if ( !fi.isFormField () ) {
	            	  
	            	 String fieldname=fi.getFieldName();
	            	 
	            	 if (!fi.getString().equals("")){
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
	           }
	          
	          CRUD.saveOrUpdate(trofeo);
	       } catch(Exception ex) {
	            System.out.println(ex);
	       }

	      response.sendRedirect("showTrophy.op");
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.ju = CRUD.executeQuery("FROM Trofeo WHERE nome ='"+request.getParameter("id")+"'");
		
		List<Gioco> gameList = CRUD.executeQuery("FROM Gioco");
		
		request.setAttribute("gioco", gameList);
		request.setAttribute("template", this.template);
		request.setAttribute("trofeo", this.ju);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}
	
	
}