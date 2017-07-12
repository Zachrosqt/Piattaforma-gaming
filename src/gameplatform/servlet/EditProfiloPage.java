package gameplatform.servlet;

import java.io.File;
import java.io.IOException;
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

import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.db.table.Template;
import gameplatform.db.table.Utente;

/**
 * Servlet implementation class EditProfiloPage
 */
@WebServlet("/EditProfiloPage")
public class EditProfiloPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfiloPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("pageName");
    	
    	this.template = service.templates(pageName);
    	
    	this.filePath = getServletContext().getInitParameter("file-upload"); 
		
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
		
		String text="true";
		int scrittura = 1;
		
		boolean isMultipart;
		long maxFileSize = 5000 * 1024;
		int maxMemSize = 4 * 1024;
		File file ;
		ServletContext app=getServletContext();
		String path=app.getRealPath("");
		
		isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(!isMultipart){
			
			text = "false form";
			
		} else {
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			   
		     // maximum size that will be stored in memory
		     factory.setSizeThreshold(maxMemSize);


		     // Create a new file upload handler
		     ServletFileUpload upload = new ServletFileUpload(factory);
		   
		     // maximum file size to be uploaded.
		     upload.setSizeMax( maxFileSize );
		     
		     try {
		     
			     List fileItems = upload.parseRequest(request);
			 	
		         // Process the uploaded file items
		         Iterator i = fileItems.iterator();
		         
		         String user = "";
		   
		         Utente testUser = null;
		         
		         while ( i.hasNext () ) {
			          FileItem fi = (FileItem)i.next();
			          if ( fi.isFormField () ) {
			        	  String fieldname = fi.getFieldName();
			        	  
			        	  if (fieldname.equals("iusername")){
			        		  user = fi.getString();
			        	  }
			        	  
			        	  if (fieldname.equals("iemail")){
			                	List<Utente> emailControl = service.userEmail(fi.getString());
			                	
			                	Iterator<Utente> cntrl = emailControl.iterator();
			                	
			                	if(cntrl.hasNext()){
			                		testUser = cntrl.next();
			                	}
			                	
			                }
			          }
			      }
		         
		         if(testUser != null && !testUser.getUsername().equals(user)){
		        	scrittura = 0;
             		text = "false email";
		         }
		         
		         if (scrittura==1){
			         Utente utente = null;
			         List<Utente> listUser = service.username(user);
			         
			         Iterator<Utente> userIt = listUser.iterator();
			         
			         if(userIt.hasNext()){
			        	 utente = userIt.next();
			         }
			         
			         text = "true";
			         
			         Iterator i2 = fileItems.iterator();
			         
			         while ( i2.hasNext () ) {
			            FileItem fi = (FileItem)i2.next();
			            if ( !fi.isFormField () ) {
			               // Get the uploaded file parameters
			               String fileName = user;
			               String contentType = fi.getContentType();
			               
				            
				           if (contentType.equals("image/png")){
					           // Write the file
					           if( fileName.lastIndexOf("\\") >= 0 ) {
					               file = new File( path + filePath + fileName + ".png") ;
					           } else {
					        	   file = new File( path + filePath + fileName + ".png") ;
					           }
					               
					           fi.write( file ) ;
				            } else {
				            	text = "false file";
				           	}
				           
								
			            } else {
			            	String fieldname = fi.getFieldName();
			                String fieldvalue = fi.getString();
			                
			                if (fieldname.equals("inome")){
			                	utente.setNome(fieldvalue);
			                } else if (fieldname.equals("icognome")){
			                	utente.setCognome(fieldvalue);
			                } else if (fieldname.equals("ieta")){
			                	utente.setEta(Integer.parseInt(fieldvalue));
			                } else if (fieldname.equals("iemail")){
			                	utente.setEmail(fieldvalue); 	
			                } else if (fieldname.equals("ipassword")){
			                	utente.setPassword(fieldvalue);
			                }
			                
			            }
			         }
			        	 
			         service.insertUser(utente);
		         }
		         
		    } catch (Exception e) {
				// TODO Auto-generated catch block
		    	text = "false";
			}
		}
	    
	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("livelli", service.livelli(request.getParameter("user")));
		request.setAttribute("giocati", service.giocare(request.getParameter("user")));
		
		request.setAttribute("template", this.template);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}


}
