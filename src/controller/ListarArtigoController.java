package controller;

import model.Artigos;
import service.ArtigoService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListarArtigo.do")
public class ListarArtigoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.setAttribute("artigo-id", "");
    	
    	ArtigoService atrService = new ArtigoService();
    	ArrayList<Artigos> artigos = atrService.listarArtigos(null);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<!DOCTYPE html><html lang=\"pt-br\"><head><meta charset='UTF-8'><link rel='stylesheet' type='text/css' href='style/main-style.css' media='screen' /><title>RealNews</title></head>");
    	out.println("<header><span class=\"page-title\">Real News</span><nav><a class=\"publicar-btn\" href=\"./CadastrarArtigo.html\">Novo artigo</a></nav></header>");
    	out.println("<div class=\"atr-wrapper\">");
    	for(Artigos atr : artigos) {
    		out.println("<div class=\"main-atr\">");
    		out.println("<h3 class=\"atr-title\">" + atr.getTitulo() + "</h3>");
            out.println("<p class=\"atr-desc\">" + atr.getDescricao() + "</p>");
            out.println("<div class=\"atr-opt\">");
            out.println("<form action='DeletarArtigo.do' method='post'><input type='hidden' name='artigo-id' value='" + atr.getId() + "'/><button class=\"atr-opt-btn\">Deletar</button></form>");
            out.println("<form action=\"AtualizarArtigo.do\" method=\"get\"><button class=\"atr-opt-btn\">Atualizar</button></form>");
            out.println("</div>");
            out.println("</div>");
    	}
    	out.println("</div>");
    	out.println("</body></html>");
    }
}
