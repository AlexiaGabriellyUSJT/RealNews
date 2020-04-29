package dao;

import java.util.ArrayList;
import java.sql.*;
import model.*;

public class ComentarioDAO {
    private Connection connection;

    public ComentarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public ComentarioDAO() {
        this(null);
    }

    public int create(Comentario comentario) {
        String create = "INSERT INTO comentario(nome, texto, fk_noticia_id) VALUES (?, ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(create)) {

            pst.setString(1, comentario.getNome());
            pst.setString(2, comentario.getTexto());
            pst.setInt(3, comentario.getArtigo().getId());
            pst.execute();

            String sqlQuery = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement stm2 = connection.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
                if (rs.next()) {
                    comentario.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }

        return comentario.getId();
    }

    public ArrayList<Comentario> read(Artigo a) {
        String read = "SELECT * FROM comentario WHERE fk_noticia_id = ?;";

        try (PreparedStatement pst = connection.prepareStatement(read)) {

            pst.setInt(1, a.getId());

            ResultSet resultado = pst.executeQuery();

            ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

            while (resultado.next()) {
                Comentario c = new Comentario();
                Artigo art = new Artigo();

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String texto = resultado.getString("texto");
                int fkArtigo = resultado.getInt("fk_noticia_id");

                c.setId(id);
                c.setNome(nome);
                c.setTexto(texto);

                art.setId(fkArtigo);
                c.setArtigo(art);
                

                comentarios.add(c);
            }

            return comentarios;

        } catch (SQLException e) {
            System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}