package gameplatform.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Template;

/**
 * Servlet implementation class GamePage
 */

public class GamePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String pageName;
	private List<Template> template;
	private GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GamePage() {
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
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//new part
		request.setAttribute("gallery", service.allImmages(request.getParameter("id")));
		request.setAttribute("gioco", service.game(request.getParameter("id")));
		request.setAttribute("template", this.template);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}

}
