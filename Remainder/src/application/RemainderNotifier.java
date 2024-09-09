package application;

import java.sql.SQLException;
import java.time.LocalDateTime;
public class RemainderNotifier {
    public void checkRemainders(RemainderManager manager) throws SQLException {
        for (Remainder Remainder : manager.getRemainder()) {
            if (!Remainder.isDone() && Remainder.getTime().isBefore(LocalDateTime.now())) {
                System.out.println("Remainder due: " + Remainder.getTitle());
            }
        }
    }
}

