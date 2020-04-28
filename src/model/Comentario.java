package model;

public class Comentario {
    private int id;
    private String nome;
    private String texto;
    private Artigo artigo;
    
    public Comentario() {
        this(0, "", "", null);
    }

    public Comentario(int id, String nome, String texto, Artigo artigo) {
        this.id = id;
        this.nome = nome;
        this.texto = texto;
        this.artigo = artigo;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }
   
    public String getTexto() {
        return this.texto;
    }
   
    public Artigo getArtigo() {
        return this.artigo;
    }
   
    public void setId(int id) {
        this.id = id;
    }
   
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }
}