package email.notification;

import notification.service.NotificationService;

public class EmailNotification implements NotificationService {
    @Override
    public void notify(String message){
        System.out.println("Email envoyé :\n" + message);
    }
}