import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;
import model.TaskNotFoundException;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        int op = 1;

        System.out.println("""
                Escolha entre as opções:
               
                1 - Criar Tarefa
                2 - Atualizar Tarefa
                3 - Remover Tarefa
                4 - Remover Todas as Tarefas
                5 - Listar Tarefa
                6 - Adicionar Dados de Teste
                
                0 - Sair
                """);
        op = Integer.parseInt(sc.nextLine());

        switch (op){
            case 0:
                System.exit(0);
                System.out.println("Programa finalizado!");
                break;
            case 1:
                addTask(op, sc, taskManager);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
            taskManager.loadTestData();
                break;
        }
    }

    private static void addTask(int op, Scanner sc, TaskManager taskManager) {
        while (op != 0) {
            System.out.println("Digite o titulo da tarefa: ");
            String title = sc.nextLine();

            System.out.println("Digite o descricao da tarefa: ");
            String description = sc.nextLine();
            Task task = new Task(title, description);

            try {
                taskManager.addTask(task);
                System.out.println("Tarefa adicionada com sucesso!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("""
            0 - Sair
            1 - Adicionar nova tarefa
            """);
            op = Integer.parseInt(sc.nextLine());
        }
    }
}