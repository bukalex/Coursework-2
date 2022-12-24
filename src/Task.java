import java.time.LocalDateTime;

public class Task {
    static int idCounter = 0;

    public enum RepeatabilityType{ONCE, DAILY, WEEKLY, MONTHLY, YEARLY}

    public enum AccessType{PRIVATE, WORK}

    private String title;
    private String description;
    private AccessType accessType;
    private LocalDateTime creationTime;
    private int id;
    private RepeatabilityType repeatabilityType;

    public Task(String title, String description){
        if(title == null || title.isEmpty() || title.isBlank()){
            this.title = "Untitled";
        }else{
            this.title = title;
        }

        if(description == null || description.isEmpty() || description.isBlank()){
            this.description = "Unknown";
        }else{
            this.description = description;
        }

        accessType = AccessType.PRIVATE;
        repeatabilityType = RepeatabilityType.ONCE;
        creationTime = LocalDateTime.now();
        id = idCounter++;
    }

    public Task(String title, String description, String accessType, String repeatabilityType){
        this(title, description);

        if(accessType == null){
            this.accessType = AccessType.PRIVATE;
        }else{
            try{
                this.accessType = AccessType.valueOf(accessType.toUpperCase());
            }
            catch (IllegalArgumentException e){
                this.accessType = AccessType.PRIVATE;
            }

        }

        if(repeatabilityType == null){
            this.repeatabilityType = RepeatabilityType.ONCE;
        }else{
            try{
                this.repeatabilityType = RepeatabilityType.valueOf(repeatabilityType.toUpperCase());
            }
            catch (IllegalArgumentException e){
                this.repeatabilityType = RepeatabilityType.ONCE;
            }

        }
    }

    public LocalDateTime showNextTime(){
        LocalDateTime nextTime = creationTime;
        switch (repeatabilityType){
            case ONCE:
                break;
            case DAILY:
                return nextTime.plusDays(1);
            case WEEKLY:
                return nextTime.plusWeeks(1);
            case MONTHLY:
                return nextTime.plusMonths(1);
            case YEARLY:
                return nextTime.plusYears(1);
        }
        return nextTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public RepeatabilityType getRepeatabilityType() {
        return repeatabilityType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "====================" + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Access type: " + accessType + "\n" +
                "Repeatability: " + repeatabilityType + "\n" +
                "ID: " + id + "\n" +
                "====================";
    }
}
