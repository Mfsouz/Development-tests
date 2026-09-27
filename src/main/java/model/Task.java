package model;

import java.time.LocalDate;

public class Task  {

     private String titulo;
     private String descricao;
     private Estado estado;
     private Tipo tipo;
     private LocalDate dataCriacao;
     private LocalDate dataLimite;
     private Prioridade prioridade;

    public enum Estado{
        EM_PROGRESSO,
        PENDENTE,
        CONCLUIDA

    }

    public enum Tipo{
        ESTUDO,
        TAREFA,
        TPCS,
        LEITURA,
        OUTRO

    }

    public enum Prioridade{
        NAO_URGENTE,
        URGENTE,
        SUPER_URGENTE

    }

    public Task(String titulo, String descricao){
          this.titulo = titulo;
          this.descricao = descricao;
          this.estado = Estado.PENDENTE;
          this.dataCriacao = LocalDate.now();
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Estado getEstado(){
        return estado;
    }
    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public Tipo getTipo(){
        return tipo;
    }
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }

    public LocalDate getDataCriacao(){
        return dataCriacao;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString(){
        return "\nTitulo: " + this.getTitulo()
         + "\nDescrição: " + this.getDescricao()
         + "\nData Criação: " + this.getDataCriacao()
         + "\nData Limite: " + this.getDataLimite()
         + "\nPrioridade: " + this.getPrioridade()
         + "\nTipo: " + this.getTipo()
         + "\nEstado " + this.getEstado()
         + "\n ";
    }
}



