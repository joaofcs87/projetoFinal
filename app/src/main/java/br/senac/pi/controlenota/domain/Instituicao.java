package br.senac.pi.controlenota.domain;

/**
 * Created by Aluno on 11/12/2015.
 */
public class Instituicao {
    private long id;
    private String instituicao;

    public Instituicao(){

    }

    public Instituicao (long id, String instituicao) {
        this.id = id;
        this.instituicao = instituicao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
