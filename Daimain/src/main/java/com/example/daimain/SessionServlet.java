package com.example.daimain;

import DAO.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {
    private HttpSession userSession = null;

    @Override
    public void init() throws ServletException {
        super.init();
        DAO.registerDriver();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email, psw;
        ServletContext ctx = getServletContext();
        email = request.getParameter("email");
        psw = request.getParameter("mdp");

        System.out.println("Debug log: "+email+" "+psw);
        if(DAO.LoginFormQuery(email,psw)){
            this.userSession = request.getSession();
            this.userSession.setAttribute("email", email);
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.html");
            System.out.println("debug log: break point");
            dispatcher.forward(request,response);

        }
    }
}
