package com.example.fernando42.jaera;


public class Mensagem {
    private final String titulo ="Bem vindo a Filiação";
    private  String Texto ="\nBem Vindo  você é o novo filiado da 42'Corporations ";
    public Mensagem(){
        Texto=MainActivity.nome.getText().toString()+Texto;
    }
        public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return Texto;
    }

}
