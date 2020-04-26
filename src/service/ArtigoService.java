package service;

import model.Artigos;
import dao.ArtigoDAO;
import dao.ConnectionFactory;

public class ArtigoService {
	ArtigoDAO artigo = new ArtigoDAO(ConnectionFactory.getConexao());
	
	public void create (Artigos atr) {
		artigo.createArtigo(atr);
	}
}
