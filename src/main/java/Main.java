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

        menuPrincipal(sc, taskManager);
    }

    private static void menuPrincipal(Scanner sc, TaskManager taskManager) throws TaskNotFoundException {
        int op = -1;
        while (op != 0) {
            System.out.println("""
                    1 - Criar Tarefa
                    2 - Atualizar Tarefa
                    3 - Remover Tarefa
                    4 - Listar Tarefa
                    5 - Adicionar Dados de Teste
                    0 - Sair
                    """);

            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 0:
                    sc.close();
                    System.out.printf("Programa finalizado!");
                    System.exit(0);
                    break;
                case 1:
                    addTask(op, sc, taskManager);
                    break;
                case 2:
                    menuUpdateTask(op, sc, taskManager);
                    break;
                case 3:
                    menuDeleteTask(op, sc, taskManager);
                    break;
                case 4:
                    menuListMenu(op, sc, taskManager);
                    break;
                case 5:
                    taskManager.loadTestData();
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }
    }

    private static void menuListMenu(int op, Scanner sc, TaskManager taskManager) {
    }

    private static void menuDeleteTask(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {

        while (op != 0) {
            System.out.println("""
                        1 - Elimar uma tarefa
                        2 - Eliminar todas as tarefas
                        0 - Voltar
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    deleteTask(op, sc, taskManager);
                    break;
                case 2:
                    taskManager.deleteAllTask();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }
    }
    private static void deleteTask(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {

        while (op != 0) {
            System.out.println("Digite o ID da tarefa: ");
            int ID = Integer.parseInt(sc.nextLine());

            try {
                taskManager.deleteTask(ID);
                System.out.println("Tarefa eliminada com sucesso!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("""
                    0 - Sair
                    1 - Eliminar outra tarefa
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());
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
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());
        }
    }

    private static void menuUpdateTask(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {
        while (op != 0) {
            System.out.println("""
                        1 - Atualizar Titulo
                        2 - Atualizar Estado
                        3 - Atualizar Prioridade
                        0 - Voltar
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    updateTitle(op, sc, taskManager);
                    break;
                case 2:
                    updateState(op, sc, taskManager);
                    break;
                case 3:
                    updatePriority(op, sc, taskManager);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }
    }
    private static void updateTitle(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {

        while (op != 0) {
            System.out.println("Digite o ID da tarefa: ");
            int ID = Integer.parseInt(sc.nextLine());

            System.out.println("Digite o novo titulo da tarefa: ");
            String newTitle = sc.nextLine();

            try {
                taskManager.updateTitle(ID, newTitle);
                System.out.println("Tarefa atualizada com sucesso!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("""
                    0 - Sair
                    1 - Atualizar outra tarefa
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());
        }
    }
    private static void updateState(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {
        Task.State newState = null;
        int stateOp;

        while (op != 0) {
            System.out.println("Digite o ID da tarefa: ");
            int ID = Integer.parseInt(sc.nextLine());

            System.out.println("Digite o novo estado da tarefa: ");
            System.out.println("""
                    1 - Pendente
                    2 - Em progresso
                    3 - Acabada
                    0 - Voltar
                    """);
            System.out.println("Escolha uma das opções:");
            stateOp = Integer.parseInt(sc.nextLine());

            switch (stateOp){
                case 1:
                    newState = Task.State.PENDING;
                    break;
                case 2:
                    newState = Task.State.IN_PROGRESS;
                    break;
                case 3:
                    newState = Task.State.FINISHED;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }

            try {
                taskManager.updateState(ID, newState);
                System.out.println("Tarefa atualizada com sucesso!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("""
                    0 - Sair
                    1 - Atualizar outra tarefa
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());
        }
    }
    private static void updatePriority(int op, Scanner sc, TaskManager taskManager) throws TaskNotFoundException {
        Task.Priority newPriority = null;
        int priorityOp;

        while (op != 0) {
            System.out.println("Digite o ID da tarefa: ");
            int ID = Integer.parseInt(sc.nextLine());

            System.out.println("Digite a nova prioridade da tarefa: ");
            System.out.println("""
                    1 - Alta
                    2 - Media
                    3 - Baixa
                    0 - Voltar
                    """);
            System.out.println("Escolha uma das opções:");
            priorityOp = Integer.parseInt(sc.nextLine());

            switch (priorityOp){
                case 1:
                    newPriority = Task.Priority.HIGH;
                    break;
                case 2:
                    newPriority = Task.Priority.MEDIUM;
                    break;
                case 3:
                    newPriority = Task.Priority.LOW;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }

            try {
                taskManager.updatePriority(ID, newPriority);
                System.out.println("Tarefa atualizada com sucesso!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("""
                    0 - Sair
                    1 - Atualizar outra tarefa
                    """);
            System.out.println("Escolha uma das opções:");
            op = Integer.parseInt(sc.nextLine());
        }
    }


}