package com.example.daimain;

import DAO.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void init() {
        DAO.registerDriver();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * TODO: controlli sui null passati dal frontend - controllo sui doppioni nel DB (email/?) gia' esistente
        *  TODO: spostamento user e pwd nel web.xml  - togliere i caratteri speciali dall'html (usare i codici ;.....)
        *
        * */
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        RequestDispatcher dispatcher;

        String nom, prenom, email, mdp, nomEntreprise, pays, adresse, numtel, date;
        nom = request.getParameter("nom");
        prenom = request.getParameter("prenom");
        email = request.getParameter("email");
        mdp = request.getParameter("mdp");
        nomEntreprise = request.getParameter("nom_entrprise");
        pays = request.getParameter("country");
        adresse = request.getParameter("adresse");
        numtel = request.getParameter("numTel");
        date = request.getParameter("birthday");
        System.out.println("Debug log: "+nom+" "+prenom+" "+email+" "+mdp+" "+nomEntreprise+" "+pays+" "+adresse+" "+numtel+" "+date);


            Date utilDate = Date.valueOf(date);
           if(DAO.RegisterFormQuery(nom,prenom,email,mdp,nomEntreprise,pays,adresse,numtel,utilDate)) {
               dispatcher = ctx.getRequestDispatcher("/index.html");
               System.out.println("debug log: break point");
               dispatcher.forward(request,response);
           }







    }
}
