#### Étape 1 : compiler les différents modules
```cmd
javac -d bin/bookapi bookapi/module-info.java bookapi/src/book/Book.java
javac -d bin/inventory --module-path bin/book-api inventory/module-info.java inventory/src/inventory/BookInventory.java
javac -d bin/checkout --module-path "bin/book-api;bin/inventory" checkout/module-info.java checkout/src/checkout/CheckoutSystem.java
javac -d bin/notification-service notification-service/module-info.java notification-service/src/notification.service/NotificationService.java 
javac -d bin/email-notification --module-path bin/notification-service email-notification/module-info.java email-notification/src/email.notification/EmailNotification.java 
javac -d bin/reports --module-path "bin/book-api;bin/inventory" reports/module-info.java reports/src/reports/InventoryReport.java 
javac -d bin/app --module-path "bin/book-api;bin/inventory;bin/checkout;bin/notification-service;bin/email-notification;bin/reports" app/module-info.java app/src/app/LibraryApp.java 
```

#### Étape 2 : exécuter avec
```cmd
java --module-path bin -m app/app.LibraryApp
```