package service;

import java.util.ArrayList;

import dao.ComentarioDAO;
import dao.ConnectionFactory;
import model.*;

public class ComentarioService {
    ComentarioDAO cDAO = new ComentarioDAO(ConnectionFactory.getConexao());

    public void create(Comentario comentario) {
        cDAO.create(comentario);
    }

    public ArrayList<Comentario> read(Artigos art) {
        return cDAO.read(art);
    }
}