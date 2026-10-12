import model.Task;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> arrayTask = new ArrayList<>();
    private int ID = 1;

    ///Metodos Principais

    //Adiciona uma nova tarefa a arrayTask
    public void addTask(Task task){
            task.setID(ID++);
            arrayTask.add(task);
    }

    //Atualiza os ESTADOS das tarefas
    public void updateState(int ID, Task.State newState){
        Task taskSearch = searchTask(ID);
            if(taskSearch != null){
                taskSearch.setState(newState);
            }else{
                throw new IllegalArgumentException("Nenhuma tarefa encontrada com o ID: " + ID);
            }
    }

    //Atualiza os TIPOS das tarefas
    public void updateType(int ID, Task.Type newType){
        Task taskSearch = searchTask(ID);
        if(taskSearch != null){
            taskSearch.setType(newType);
        }else{
            System.out.println("\nNenhuma tarefa com o ID: " + ID);
        }
    }

    //Atualiza as PRIORIDADES das tarefas
    public void updatePriority(int ID, Task.Priority newPriority){
        Task taskSearch = searchTask(ID);
        if(taskSearch != null){
            taskSearch.setPriority(newPriority);
        }else{
            System.out.println("\nNenhuma tarefa com o ID: " + ID);
        }
    }

    //Eliminar a tarefas que o utilizador escolher
    public void deleteTask(int ID){
        arrayTask.removeIf(task -> (task.getID() == ID));
    }

    //Eliminar TODAS as tarefas
    public void deleteAllTask(){
        arrayTask.clear();
        ID = 1;
    }


    //Vai buscar e listar todas as tarefas sem filtros
    public List<Task> getAllTasks() {
        return new ArrayList<>(arrayTask);
    }

    //Vai buscar e listar todas as tarefas que estao com o estado "CONCLUIDA"
    public List<Task> getDoneTasks() {
        List<Task> doneTasks = new ArrayList<>();

        for (Task task : arrayTask) {
            if (task.getState() == Task.State.FINISHED) {
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }


    //Dados de teste
    public void loadTestData() {
        Task task1 = new Task(1, "Estudar Java", "Estudar classes, métodos e objetos");
        Task task2 = new Task(2, "Fazer trabalho de Matemática", "Resolver os exercícios da ficha");
        Task task3 = new Task(3, "Ler livro", "Ler 30 páginas do livro");
        Task task4 = new Task(4, "Fazer exercício", "Treino de peito e costas");
        Task task5 = new Task(5, "Projeto JavaFX", "Continuar o desenvolvimento do Task Manager");
        arrayTask.add(task1);
        arrayTask.add(task2);
        arrayTask.add(task3);
        arrayTask.add(task4);
        arrayTask.add(task5);

        ID = arrayTask.size() + 1;
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
    private Task searchTask(String Title) {
        for (Task task : arrayTask) {
            if (compareStrings(task.getTitle(), Title)) {
                return task;
            }
        }
        return null;
    }

    private Task searchTask(int ID){
        for (Task task : arrayTask) {
            if (task.getID() == ID) {
                return task;
            }
        }
        return null;
    }
}
