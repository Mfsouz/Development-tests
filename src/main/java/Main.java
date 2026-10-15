import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label texto = new Label("Olá JavaFX!");

        Scene scene = new Scene(texto, 400, 300);

        stage.setTitle("Projeto JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // launch();
        TaskManager task = new TaskManager();
        task.loadTestData();

        try {
                task.updatePriority(1, Task.Priority.MEDIUM);
                task.updatePriority(3, Task.Priority.MEDIUM);
                task.updatePriority(4, Task.Priority.MEDIUM);
                task.updateState(1, Task.State.IN_PROGRESS);
                task.updateState(3, Task.State.IN_PROGRESS);
                task.updateState(4, Task.State.FINISHED);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nAqui estão todas as tarefas CONCLUIDAS: " + task.getTasksWithSomeState(Task.State.FINISHED).size());
        System.out.println("\nAqui estão todas as tarefas: " + task.getAllCompleteTasks());
        System.out.println("\nAqui estão todas as tarefas com prioridade media e em progresso: " + task.getTasksWithSomePriorityAndSomeState(Task.Priority.MEDIUM, Task.State.IN_PROGRESS));
        System.out.println("\nAqui estão todas as tarefas CONCLUIDAS: " + task.getTasksWithSomeState(Task.State.FINISHED));
    }


}