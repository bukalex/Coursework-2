import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static Diary diary = new Diary();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTasksForDay(scanner);
                            break;
                        case 4:
                            diary.getRemovedTasks();
                            break;
                        case 5:
                            editTask(scanner);
                            break;
                        case 6:
                            diary.getSortedByDate();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskTitle = scanner.next();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.next();
        System.out.print("Введите тип доступа задачи (PRIVATE / WORK): ");
        String taskAccessType = scanner.next();
        System.out.print("Введите повторяемость задачи (ONCE / DAILY / WEEKLY / MONTHLY / YEARLY): ");
        String taskRepeatabilityType = scanner.next();
        Task newTask = new Task(taskTitle, taskDescription, taskAccessType, taskRepeatabilityType);
        diary.addTask(newTask);
    }

    private static void removeTask(Scanner scanner){
        System.out.print("Введите название задачи: ");
        String taskTitle = scanner.next();
        diary.removeTask(taskTitle);
    }

    private static void getTasksForDay(Scanner scanner){
        System.out.print("Введите дату (гггг-мм-дд): ");
        String stringDate = scanner.next();
        LocalDate date;
        try{
            date = LocalDate.parse(stringDate);
            diary.getTasksForDay(date);
        } catch (DateTimeParseException e){
            System.out.println("Неверный формат данных!");
            getTasksForDay(scanner);
        }
    }

    private static void editTask(Scanner scanner){
        System.out.print("Введите название задачи: ");
        String taskTitle = scanner.next();
        try{
            Task task = diary.getTask(taskTitle);
            System.out.print("Введите новое название задачи: ");
            String newTaskTitle = scanner.next();
            System.out.print("Введите новое описание задачи: ");
            String newTaskDescription = scanner.next();
            task.setTitle(newTaskTitle);
            task.setDescription(newTaskDescription);
        } catch (NullPointerException e){
            System.out.println("Такой задачи нет!");
            diary.getAllTasks();
            editTask(scanner);
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу" + "\n" +
                "2. Удалить задачу" + "\n" +
                "3. Получить задачу на указанный день" + "\n" +
                "4. Получить удаленные задачи" + "\n" +
                "5. Редактировать задачу" + "\n" +
                "6. Получить задачи по датам" + "\n" +
                "0. Выход");
    }
}