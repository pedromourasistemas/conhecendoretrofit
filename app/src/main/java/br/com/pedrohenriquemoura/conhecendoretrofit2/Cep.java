package br.com.pedrohenriquemoura.conhecendoretrofit2;

public class Cep
{
    private String bairro;
    private String cidade;
    private String logradouro;
    private String estado;

    public Cep(String bairro, String cidade, String logradouro, String estado) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getEstado() {
        return estado;
    }
}