import model.Task;
import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> arrayTask = new ArrayList<>();


    public void addTask(Task task){
        arrayTask.add(task);
    }

    public void updateEstado(String Titulo, Task.Estado novoEstado){
        boolean encontrada = false;
        for (Task task: arrayTask){
            if(task.getTitulo().equals(Titulo)){
                task.setEstado(novoEstado);
                encontrada = true;
            }
        }
        if (!encontrada){
            System.out.println("Nenhuma tarefa com esse nome");
        }
    }

    public void deleteTask(String Titulo){
        arrayTask.removeIf(task -> task.getTitulo().equals(Titulo));
    }

    public void deleteAllTask(){
        arrayTask.clear();
    }





    public ArrayList<Task> getDoneTask() {
        ArrayList<Task> doneTask = new ArrayList<>();

        for (Task task : arrayTask) {
            if (task.getEstado() == Task.Estado.CONCLUIDA) {
                doneTask.add(task);
            }
        }
        return doneTask;
    }


}
