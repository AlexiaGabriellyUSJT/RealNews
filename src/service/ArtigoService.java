package service;

import model.Artigos;
import service.ArtigoService;

import java.util.ArrayList;

import dao.ArtigoDAO;
import dao.ConnectionFactory;

public class ArtigoService {
	ArtigoDAO artigo = new ArtigoDAO(ConnectionFactory.getConexao());
	
	public void create (Artigos atr) {
		artigo.createArtigo(atr);
	}
	
	public ArrayList<Artigos> listarArtigos(Artigos atr) {
		return artigo.listarArtigos();
	}
}
