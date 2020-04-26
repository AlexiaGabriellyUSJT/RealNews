package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.Artigos;
import service.ArtigoService;

@WebServlet("/PublicarArtigo.do")
public class CriarArtigoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String texto = request.getParameter("texto");

        ArtigoService artigo = new ArtigoService();
        artigo.create(new Artigos(0, titulo, descricao, texto, null));
        
        PrintWriter out = response.getWriter();
        out.println(titulo + descricao + texto);
    }
}
