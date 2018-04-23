package com.example.mailson.tcc.DTO;

import java.io.Serializable;

public class jsonVisionPost  implements Serializable {


    private String cpfCNH;
    private String cpfDoc;
    private String Nome;
    private String Placa;
    private String Renavam;
    private String Modela;
    private String Senha;
    private boolean sucesso;

    public jsonVisionPost() {
    }

    public String getCpf() {
        return cpfCNH;
    }

    public void setCpf(String cpf) {
        this.cpfCNH = cpf;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getCpfDoc() {
        return cpfDoc;
    }

    public void setCpfDoc(String cpfDoc) {
        this.cpfDoc = cpfDoc;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getRenavam() {
        return Renavam;
    }

    public void setRenavam(String renavam) {
        Renavam = renavam;
    }

    public String getModela() {
        return Modela;
    }

    public void setModela(String modela) {
        Modela = modela;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }


/*
    private  List<Requests> requests = new List<Requests>();

    private  List<String> features;
    private  String type;

    public jsonVisionPost(String stringImg){
        //montar o objeto pro json
        Requests objRequest = new Requests();
        objRequest.image = stringImg;


        requests.add()/
    }
*/
}

/*
public  class  Requests{
    String image;
}*/
