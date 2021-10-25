

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShoppingListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") == null){
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
        }
        else if(request.getParameter("action").equals("logout")) {
            request.getSession().invalidate();
            items.clear();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
        }
    }
    ArrayList<String> items = new ArrayList<String>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");    
        session.setAttribute("items", items);
        String item = request.getParameter("item");
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        
        if (action.equals("register")) {  
            String username = request.getParameter("username");
            session.setAttribute("username", username); 
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;
        }
        else if(action.equals("add")) {
            items.add(items.size(), item);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;
        }
        
        else if (action.equals("delete")) {
            items.remove(request.getParameter("item"));
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;
        }
        
        
        
    }

}
