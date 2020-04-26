package dao;
import model.Artigos;
import java.sql.*;
import java.util.*;

public class ArtigoDAO {
	private Connection connection;
	
	public ArtigoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArtigoDAO() {
		this(null);
	}
	
	//Método Create
	public int createArtigo(Artigos artigo) {
		String create = "INSERT INTO noticia(titulo, descricao, texto) VALUES(?, ?, ?)";
		
		try (PreparedStatement pst = connection.prepareStatement(create)){
			pst.setString(1, artigo.getTitulo());
			pst.setString(2, artigo.getDescricao());
			pst.setString(3, artigo.getTexto());
			pst.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = connection.prepareStatement(sqlQuery);
				 ResultSet rs = stm2.executeQuery()) {
				
				if(rs.next()) artigo.setId(rs.getInt(1));
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
            System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }
		
		return artigo.getId();
	}
	

}
