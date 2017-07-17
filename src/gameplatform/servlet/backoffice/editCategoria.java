package gameplatform.servlet.backoffice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Categoria;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Template;
import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.business.impl.GameplatformCRUDImpl;


public class editCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	private GameplatformCRUD CRUD = GameplatformCRUDImpl.getGameplatformCRUDImpl();
	private String v;
	private Categoria category=new Categoria();


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCategoria() {
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

			this.v=request.getParameter("id");	
			this.category.setCategoria(this.v);
			CRUD.delete(this.category);
		process(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.category.setCategoria(request.getParameter("name"));
		CRUD.saveOrUpdate(category);
				process(request, response);
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		request.setAttribute("categoria", this.v);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/backoffice/index.jsp");
		view.forward(request, response);
	}
	
	
}