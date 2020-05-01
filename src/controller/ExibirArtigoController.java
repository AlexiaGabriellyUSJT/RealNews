package controller;
import model.Artigos;
import model.Comentario;
import service.ComentarioService;
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

@WebServlet("/OpenArtigo.do")
public class ExibirArtigoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	int id = 0;
    	HttpSession session = request.getSession();
    	ArtigoService atrService = new ArtigoService();
    	ComentarioService comService = new ComentarioService();
    	
    	if(session.getAttribute("artigo-id").equals("")) {
    		id = Integer.parseInt(request.getParameter("artigo-id"));
    		session.setAttribute("artigo-id", id);
    	}
    	
    	Artigos atr = new Artigos(id, "", "", "", null);
    	Artigos artigo = atrService.exibirArtigo(atr);
    	ArrayList<Comentario> comentarios = comService.read(atr);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<!DOCTYPE html><html lang=\"pt-br\"><head><meta charset='UTF-8'><link rel=\"stylesheet\" href=\"./style/cadastro-artigo.css\"><link rel='stylesheet' type='text/css' href='style/main-style.css' media='screen' /><title>RealNews</title></head>");
    	out.println("<header><span class=\"page-title\">Real News</span><nav><a class=\"publicar-btn\" href=\"./CadastrarArtigo.html\">Novo artigo</a></nav></header>");
    	out.println("<div class=\"atr-wrapper\">");
    	out.println("<div>");
    	out.println("<h3>" + artigo.getTitulo() + "</h3>");
    	out.println("<p>" + artigo.getTexto() + "</p>");
        out.println("<div class='comentarios-content'><h3 class='titulo'> Comentarios</h3>");
        
        out.println("<div class='comentarios-wrapper'>");
        for(Comentario c : comentarios) {
            out.println("<div class='comentario'><p class='nome'>"+c.getNome()+"</p><p class='texto'>"+c.getTexto()+"</p></div>");
        }
        
        out.println("</div>");
        out.println("<form action='NovoComentario.do' method='post' class='novo-comentario'>");
        out.println("<p class='titulo '>Adicionar novo comentario</p>");
        out.println("<input type='text' name='nome' placeholder='Nome'/>");
        out.println("<textarea type='text' name='texto' placeholder='Escreva aqui seu comentario'></textarea>");
        out.println("<button class=\"publicar-btn\" type='submit'>Adicionar</button>");
        out.println("</form>");
        out.println("</div>");
    	out.println("</div>");
    	out.println("</div>");
    	
    }

}
