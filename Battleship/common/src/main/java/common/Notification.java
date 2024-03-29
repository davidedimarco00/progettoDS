package common;

public class Notification {
    private String message;
    private String title;
    private Player sender;

    public Notification(String message, String title, Player sender) {
        this.message = message;
        this.title = title;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

}
