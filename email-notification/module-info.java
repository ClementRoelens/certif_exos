module email.notification {
    requires notification.service;
    provides notification.service.NotificationService with email.notification.EmailNotification;
    exports email.notification;
}