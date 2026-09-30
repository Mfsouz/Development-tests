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

        task.updateState("Estudar Java", Task.State.FINISHED);

        System.out.println("\nAqui estão todas as tarefas: " + task.getAllTasks());
        System.out.println("\nAqui estão todas as tarefas CONCLUIDAS: " + task.getDoneTasks());
    }


}