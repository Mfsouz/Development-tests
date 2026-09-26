import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;

import java.util.ArrayList;

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

        ArrayList<Task> arrayTask = new ArrayList<>();

        Task task1 = new Task("Matematica", "Estudar para o teste");
        Task task2 = new Task("Historia", "Estudar exame");

        arrayTask.add(task1);
        arrayTask.add(task2);
        task1.setEstado(Task.Estado.CONCLUIDA);

        System.out.println(arrayTask);
    }
}