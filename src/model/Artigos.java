package model;

import java.util.ArrayList;

public class Artigos {
    private int id;
    private String titulo;
    private String descricao;
    private String texto;
    private ArrayList<Comentario> comentario;

    public Artigos(int id, String titulo, String descricao, String texto, ArrayList<Comentario> comentario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.texto = texto;
        this.comentario = comentario;
    }

    //Construtor

    public Artigos() {
    	this(0, "", "", "", null);
	}
    
  //Métodos de get

	public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getTexto() {
        return this.texto;
    }

    public ArrayList<Comentario> getComentario() {
        return this.comentario;
    }

    //Métodos de set
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setComentario(ArrayList<Comentario> comentario) {
        this.comentario = comentario;
    }

}
