module app {
    requires book.api;
    requires inventory;
    requires checkout;
    requires notification.service;
    requires email.notification;
    requires reports;
    exports app;
}