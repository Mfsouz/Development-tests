import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;
import model.TaskNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label texto = new Label("Olá JavaFX!");

        Scene scene = new Scene(texto, 400, 300);

        stage.setTitle("Projeto JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws TaskNotFoundException {
        // launch();
        TaskManager task = new TaskManager();


        try {
            task.loadTestData();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
            task.printTasksTable();
    }


}