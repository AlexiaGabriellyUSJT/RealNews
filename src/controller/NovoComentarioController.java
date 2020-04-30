package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import service.*;

@WebServlet("/NovoComentario.do")
public class NovoComentarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int idArtigos = (Integer) session.getAttribute("artigo-id");
        
        String nome = request.getParameter("nome");
        String texto = request.getParameter("texto");

        Artigos art = new Artigos();
        art.setId(idArtigos);
        Comentario com = new Comentario(0, nome, texto, art);

        ComentarioService comService = new ComentarioService();
        comService.create(com);

        PrintWriter out = response.getWriter();
        out.println("Comentario criado com sucesso");
    }
}