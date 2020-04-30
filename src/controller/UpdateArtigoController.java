package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ArtigoService;
import model.Artigos;

@WebServlet("/UpdateArtigo.do")
public class UpdateArtigoController extends HttpServlet {
private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
    	HttpSession session = request.getSession();
    	ArtigoService atrService = new ArtigoService();
    	
    	if(session.getAttribute("artigo-id").equals("")) {
    		id = Integer.parseInt(request.getParameter("artigo-id"));
    		session.setAttribute("artigo-id", id);
    	}
    	
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String texto = request.getParameter("texto");
        
        Artigos artigo = new Artigos(id, titulo, descricao, texto, null);
        atrService.updateArtigo(artigo);
        
        PrintWriter out = response.getWriter();
        out.println("atualizado com sucesso");
    }
}
