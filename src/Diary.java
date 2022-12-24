import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Diary {
    private Map<String, Task> tasks = new LinkedHashMap<>();
    private Map<String, Task> removedTasks = new HashMap<>();

    public void addTask(Task task){
        tasks.put(task.getTitle(), task);
    }

    public void removeTask(String title){
        removedTasks.put(title, tasks.get(title));
        tasks.remove(title);
    }

    public void getTasksForDay(LocalDate date){
        for(Map.Entry<String, Task> task : tasks.entrySet()){
            switch (task.getValue().getRepeatabilityType()){
                case ONCE:
                case DAILY:
                    System.out.println(task.getValue().toString());
                    break;
                case WEEKLY:
                    if(date.getDayOfWeek().equals(task.getValue().getCreationTime().getDayOfWeek())){
                        System.out.println(task.getValue().toString());
                    }
                    break;
                case MONTHLY:
                    if(date.getDayOfMonth() == task.getValue().getCreationTime().getDayOfMonth()){
                        System.out.println(task.getValue().toString());
                    }
                    break;
                case YEARLY:
                    if(date.getDayOfYear() == task.getValue().getCreationTime().getDayOfYear()){
                        System.out.println(task.getValue().toString());
                    }
                    break;
            }
        }
    }

    public void getAllTasks(){
        for(Map.Entry<String, Task> task : tasks.entrySet()){
            System.out.println(task.getValue().toString());
        }
    }

    public void getRemovedTasks(){
        for(Map.Entry<String, Task> task : removedTasks.entrySet()){
            System.out.println(task.getValue().toString());
        }
    }

    public void getSortedByDate(){
        Map<LocalDateTime, ArrayList<Task>> tasksByDate = new LinkedHashMap<>();
        for(Map.Entry<String, Task> task : tasks.entrySet()){
            try{
                tasksByDate.get(task.getValue().getCreationTime()).add(task.getValue());
            } catch (NullPointerException e){
                tasksByDate.put(task.getValue().getCreationTime(), new ArrayList<>());
                tasksByDate.get(task.getValue().getCreationTime()).add(task.getValue());
            }
        }

        for(Map.Entry<LocalDateTime, ArrayList<Task>> taskList : tasksByDate.entrySet()){
            System.out.println(taskList.getKey());
            for(Task task : taskList.getValue()){
                System.out.println(task.toString());
            }
        }
    }

    public Task getTask(String title){
        return tasks.get(title);
    }
}
