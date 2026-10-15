package model;

import java.time.LocalDate;

public class Task  {
     private int ID;
     private String title;
     private String description;
     private State state;
     private Type type;
     final private LocalDate creationDate;
     private LocalDate dueDate;
     private Priority priority;

    public enum State{
        PENDING,
        IN_PROGRESS,
        FINISHED,

    }

    public enum Type{
        ESTUDAR,
        TAREFAS,
        TPCS,
        LEITURA,
        OUTRO
    }

    public enum Priority{
        LOW,
        MEDIUM,
        HIGH
    }

    public Task(int ID, String title, String description){
          this.ID = ID;
          if(title == null || title.isBlank()){throw new IllegalArgumentException("O Titulo não pode ser vazio");}
          this.title = title;
          this.description = description;
          this.state = State.IN_PROGRESS;
          this.creationDate = LocalDate.now();
    }
    public Task(String title){
        this.title = title;
        this.creationDate = LocalDate.now();
    }
    public int getID(){ return ID; }
    public void setID(int ID){this.ID = ID; }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        if(title == null || title.isBlank()){throw new IllegalArgumentException("O Titulo não pode ser vazio");}
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public State getState(){
        return state;
    }
    public void setState(State state){
        this.state = state;
    }

    public Type getType(){
        return type;
    }
    public void setType(Type type){
        this.type = type;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString(){
        return "\nID: " + this.getID()
         +"\nTitulo: " + this.getTitle()
         + "\nDescrição: " + this.getDescription()
         + "\nData Criação: " + this.getCreationDate()
         + "\nData Limite: " + this.getDueDate()
         + "\nPrioridade: " + this.getPriority()
         + "\nTipo: " + this.getType()
         + "\nEstado: " + this.getState()
         + "\n ";
    }
}



