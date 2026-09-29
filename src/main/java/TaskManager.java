import model.Task;

import java.text.Normalizer;
import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> arrayTask = new ArrayList<>();

    ///Metodos Principais

    //Adicionna uma nova tarefa a arrayTask
    public void addTask(Task task){
        arrayTask.add(task);
    }

    //Atualiza os ESTADOS das tarefas
    public void updateEstado(String Titulo, Task.Estado novoEstado){
        Task taskSearch = searchTask(Titulo);
            if(taskSearch != null){
                taskSearch.setEstado(novoEstado);
            }else{
                System.out.println("Nenhuma tarefa com o titulo: " + Titulo);
            }
    }

    //Atualiza os TIPOS das tarefas
    public void updateTipo(String Titulo, Task.Tipo novoTipo){
        Task taskSearch = searchTask(Titulo);
        if(taskSearch != null){
            taskSearch.setTipo(novoTipo);
        }else{
            System.out.println("\nNenhuma tarefa com o titulo: " + Titulo);
        }
    }

    //Atualiza as PRIORIDADES das tarefas
    public void updatePrioridade(String Titulo, Task.Prioridade novaPrioridade){
        Task taskSearch = searchTask(Titulo);
        if(taskSearch != null){
            taskSearch.setPrioridade(novaPrioridade);
        }else{
            System.out.println("\nNenhuma tarefa com o titulo: " + Titulo);
        }
    }

    //Eliminar a tarefas que o utilizador escolher
    public void deleteTask(String Titulo){
        arrayTask.removeIf(task -> compareStrings(task.getTitulo(),Titulo));
    }

    //Eliminar TODAS as tarefas
    public void deleteAllTask(){
        arrayTask.clear();
    }


    //Vai buscar e listar todas as tarefas sem filtros
    public ArrayList<Task> getAllTask() {
        return arrayTask;
    }

    //Vai buscar e listar todas as tarefas que estao com o estado "CONCLUIDA"
    public ArrayList<Task> getDoneTask() {
        ArrayList<Task> doneTask = new ArrayList<>();

        for (Task task : arrayTask) {
            if (task.getEstado() == Task.Estado.CONCLUIDA) {
                doneTask.add(task);
            }
        }
        return doneTask;
    }


    //Dados de teste
    public void carregarDadosTeste() {
        Task task1 = new Task("Estudar Java", "Estudar classes, métodos e objetos");
        Task task2 = new Task("Fazer trabalho de Matemática", "Resolver os exercícios da ficha");
        Task task3 = new Task("Ler livro", "Ler 30 páginas do livro");
        Task task4 = new Task("Fazer exercício", "Treino de peito e costas");
        Task task5 = new Task("Projeto JavaFX", "Continuar o desenvolvimento do Task Manager");

        arrayTask.add(task1);
        arrayTask.add(task2);
        arrayTask.add(task3);
        arrayTask.add(task4);
        arrayTask.add(task5);
    }

    ///Metodos auxiliares

    //Compara as strings, e normaliza, ou seja, mete todos os textos sem acentos e ignora CAPS LOCKS ou LOWER CASES
    private boolean compareStrings(String text1, String text2){
        text1 = Normalizer.normalize(text1, Normalizer.Form.NFD);
        text2 = Normalizer.normalize(text2, Normalizer.Form.NFD);
        text1 = text1.replaceAll("\\p{M}", "");
        text2 = text2.replaceAll("\\p{M}", "");

        return text1.equalsIgnoreCase(text2);
    }

    //Vai procurar uma tarefa por titulo, se encontrar da return da mesma, se nao da return de "nada"
    private Task searchTask(String Titulo) {
        for (Task task : arrayTask) {
            if (compareStrings(task.getTitulo(), Titulo)) {
                return task;
            }
        }
        return null;
    }
}
