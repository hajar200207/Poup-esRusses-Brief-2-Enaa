import java.text.SimpleDateFormat;
import java.util.Date;

class Action {
    private final String action;
    private final String timeStamp;

    public Action(String action) {
        this.action = action;
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String toString() {
        return "[" + timeStamp + "] " + action;
    }
}

