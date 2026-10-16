import model.Task;
import model.TaskNotFoundException;

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
    public void updateState(int ID, Task.State newState) throws TaskNotFoundException {
        Task taskSearch = searchTask(ID);
            if(taskSearch != null){
                taskSearch.setState(newState);
            }else{
                throw new TaskNotFoundException("Nenhuma tarefa encontrada com o ID: " + ID);
            }
    }

    //Atualiza os TIPOS das tarefas
    public void updateType(int ID, Task.Type newType) throws TaskNotFoundException {
        Task taskSearch = searchTask(ID);
        if(taskSearch != null){
            taskSearch.setType(newType);
        }else{
            throw new TaskNotFoundException("Nenhuma tarefa com o ID: " + ID);
        }
    }
    //Atualiza os TITULOS das tarefas
    public void updateTitle(int ID, String newTitle) throws TaskNotFoundException {
        Task taskSearch = searchTask(ID);
        if(taskSearch != null){
            taskSearch.setTitle(newTitle);
        }else{
            throw new TaskNotFoundException("Nenhuma tarefa com o ID: " + ID);
        }
    }

    //Atualiza as PRIORIDADES das tarefas
    public void updatePriority(int ID, Task.Priority newPriority) throws TaskNotFoundException {
        Task taskSearch = searchTask(ID);
        if(taskSearch != null){
            taskSearch.setPriority(newPriority);
        }else{
            throw new TaskNotFoundException("Nenhuma tarefa com o ID: " + ID);
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

    public List<Task> getAllTasks() {
        return List.copyOf(arrayTask);
    }


    //Vai buscar e listar todas as tarefas sem filtros
    public List<String> getAllTitleTasks() {
        List<String> titles = arrayTask.stream()
                .map(task -> task.getTitle())
                .toList();

       return titles;
    }

    public List<Task> getTasksWithSomeState(Task.State state) {
        return arrayTask.stream()
                .filter(task -> task.getState() == state)
                .toList();
    }

    public List<Task> getTasksWithSomePriority(Task.Priority priority) {
        return arrayTask.stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }

    public List<Task> getTasksWithSomePriorityAndSomeState(Task.Priority priority,  Task.State state) {
        return arrayTask.stream()
                .filter(task -> task.getPriority() == priority && task.getState() == state)
                .toList();
    }

    public List<Task> getOrderedTasks() {
        return arrayTask.stream()
                .sorted((task1, task2) -> task1.getTitle().compareToIgnoreCase(task2.getTitle()))
                .toList();
    }

    public void printTasksTable(){
        int titleSize = titleMinMaxLenght();
        int descriptionSize = descriptionMinMaxLenght();

        System.out.printf("| %-4s | %-" + titleSize + "s | %-" + descriptionSize + "s | %-15s | %-15s | %-15s | %-15s |%n",
                          "ID", "Titulo", "Descrição", "Estado", "Tipo", "Prioridade", "Data de Criação");

        for (Task task : arrayTask) {
            System.out.printf("| %-4d | %-" + titleSize + "s | %-" + descriptionSize+ "s | %-15s | %-15s | %-15s | %-15s |%n",
                                (task.getID()),
                                formatCell(task.getTitle(), titleSize),
                                formatCell(task.getDescription(), descriptionSize),
                                formatValues(task.getState()),
                                formatValues(task.getType()),
                                formatValues(task.getPriority()),
                                formatValues(task.getCreationDate()));
        }
    }

    //Dados de teste
    public void loadTestData() throws TaskNotFoundException {
        Task task1 = new Task(1, "Estudar Java asdasdasdasdasdadasdadasdasda", "Estudar classes");
        Task task2 = new Task(2, "Fazer trabalho de Matemática", "Resolver os exercícios");
        Task task3 = new Task(3, "Ler livro", "Ler 30 páginas do livro");
        Task task4 = new Task(4, "Fazer exercício", "Treino de peito e costas");
        Task task5 = new Task(5, "Trabalho JavaFX", "Continuar APP");
        arrayTask.add(task1);
        arrayTask.add(task2);
        arrayTask.add(task3);
        arrayTask.add(task4);
        arrayTask.add(task5);

        updatePriority(1, Task.Priority.MEDIUM);
        updatePriority(3, Task.Priority.MEDIUM);
        updatePriority(4, Task.Priority.MEDIUM);
        updateState(1, Task.State.IN_PROGRESS);
        updateState(3, Task.State.IN_PROGRESS);
        updateState(4, Task.State.FINISHED);


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
    private Task searchTask(String title) {
        for (Task task : arrayTask) {
            if (compareStrings(task.getTitle(), title)) {
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

    private int titleMinMaxLenght(){
        int titleWidth = arrayTask.stream()
                .mapToInt(task -> task.getTitle().length())
                .max()
                .orElse(5);

        titleWidth = Math.clamp(titleWidth, 15, 30);
        return titleWidth;
    }

    private int descriptionMinMaxLenght(){
        int descriptionWidth = arrayTask.stream()
                .mapToInt(task -> task.getDescription() == null ? 0 : task.getDescription().length())
                .max()
                .orElse(5);

        descriptionWidth = Math.clamp(descriptionWidth, 15, 30);
        return descriptionWidth;
    }

    private String formatCell(String text, int width) {

        if (text == null || text.isBlank()) {
            text = "N/A";
        }
        if (text.length() > width) {
            return text.substring(0, width - 3) + "...";
        }
        return text;
    }

    private String formatValues(Object value){
        return value == null ? "N/A" : value.toString();
    }
}
