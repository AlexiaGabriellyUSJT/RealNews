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

@WebServlet("/AtualizarArtigo.do")
public class ExibeEditArtigoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	int id = 0;
    	HttpSession session = request.getSession();
    	ArtigoService atrService = new ArtigoService();
    	
    	if(session.getAttribute("artigo-id").equals("")) {
    		id = Integer.parseInt(request.getParameter("artigo-id"));
    		session.setAttribute("artigo-id", id);
    	}
    	
    	Artigos atr = new Artigos();
    	atr.setId(id);
    	Artigos artigo = atrService.exibirArtigo(atr);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<!DOCTYPE html><html lang=\"pt-br\"><head><meta charset='UTF-8'><link rel=\"stylesheet\" href=\"./style/cadastro-artigo.css\"><link rel='stylesheet' type='text/css' href='style/main-style.css' media='screen' /><title>RealNews</title></head>");
    	out.println("<header><span class=\"page-title\">Real News</span><nav><a class=\"publicar-btn\" href=\"./CadastrarArtigo.html\">Novo artigo</a></nav></header>");
    	out.println("<div class=\"atr-wrapper\" method='post'>");
    	out.println("<form action=\"UpdateArtigo.do\">");
    	out.println("<label for=\"titulo\">Titulo</label>");
    	out.println("<input type=\"text\" name=\"titulo\" value='" + artigo.getTitulo() + "'>");
    	out.println("<label for=\"descricao\">Descricao</label>");
    	out.println("<input type=\"text\" name=\"descricao\" value='" + artigo.getDescricao() + "'>");
    	out.println("<label for=\"texto\">Seu artigo</label>");
    	out.println("<textarea name=\"texto\" type=\"text\" placeholder='" + artigo.getTexto() + "'></textarea>");
    	out.println("<button type=\"submit\" class=\"publicar-btn\">Atualizar</button>");
    	out.println("</form>");
    	out.println("</div>");
    }
}
