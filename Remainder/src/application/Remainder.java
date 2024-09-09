package application;

import java.time.LocalDateTime;


public class Remainder {
    private String title;
    private LocalDateTime time;
    private boolean isDone;


    public Remainder(String title, LocalDateTime time, boolean isDone){
        this.title = title;
        this.time = time;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public LocalDateTime getTime(){
        return time;
    }
    public void setTime(LocalDateTime time){
        this.time = time;
    }
    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean done){
        isDone = done;
    }

    @Override
    public String toString(){
        return "Remainder {" + "title = " + title + " , time " + time + " , isDone = " + isDone + "}";
    }
}
