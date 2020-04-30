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
	
	//Método Read
	public ArrayList<Artigos> listarArtigos() {
		String listar = "SELECT * FROM noticia;";
		
		try (PreparedStatement pst = connection.prepareStatement(listar)) {

            ResultSet resultado = pst.executeQuery();

            ArrayList<Artigos> artigo = new ArrayList<Artigos>();

            while (resultado.next()) {
                Artigos atr = new Artigos(0, "", "", "", null);

                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String texto = resultado.getString("texto");

                atr.setId(id);
                atr.setTitulo(titulo);
                atr.setDescricao(descricao);
                atr.setTexto(texto);

                artigo.add(atr);
            }

            return artigo;

        } catch (SQLException e) {
            System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }
		return null;
	}
	
	//Método Delete
	public void deletarArtigo(Artigos atr) {
		String deletarNoticia = "DELETE FROM noticia WHERE id = ?;";
		
		try (PreparedStatement pst = connection.prepareStatement(deletarNoticia)) {
			pst.setInt(1, atr.getId());
			pst.execute();
		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }
	}

	//Método Read para um único artigo
	public Artigos exibirArtigo(Artigos atr) {
		String exibirArtigo = "SELECT * FROM noticia WHERE id = ?;";
		
		try(PreparedStatement pst = connection.prepareStatement(exibirArtigo)) {
			
			pst.setInt(1, atr.getId());
			ResultSet resultado = pst.executeQuery();
			Artigos artigo = new Artigos();
			
			if(resultado.next()) {
				int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String texto = resultado.getString("texto");

                artigo.setId(id);
                artigo.setTitulo(titulo);
                artigo.setDescricao(descricao);
                artigo.setTexto(texto);
			}
			
			return artigo;
			
		} catch (SQLException e) {
            System.err.println("Falha no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Falha no java: " + e.getMessage());
            e.printStackTrace();
        }
		
		return null;
	}
	
	//Método Update
	
	public void updateArtigo(Artigos atr) {
		String updateArtigo = "UPDATE noticia SET titulo = ?, descricao = ?, texto = ? WHERE id = ?;";
		
		try(PreparedStatement pst = connection.prepareStatement(updateArtigo)) {
			pst.setString(1, atr.getTitulo());
			pst.setString(2, atr.getDescricao());
			pst.setString(3, atr.getTexto());
			pst.setInt(4, atr.getId());
			pst.execute();
		} catch (SQLException e) {
	        System.err.println("Falha no banco: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Falha no java: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}
