API REST de quizzs top méga fun !
=================================

Endpoints disponibles
---------------------

| Méthode | Endpoint | Description                                                                        |
|---------|----------|------------------------------------------------------------------------------------|
| **GET** | `/quizzes` | Récupérer la liste de tous les quizzs, dont le quiz d'exemple automatiquement créé |
| **GET** | `/quizzes/{id}` | Récupérer le quizz d'id spécifié, ou rien si l'id n'existe pas                     |
| **POST** | `/quizzes` | Ajouter un nouveau quizz avec ses questions et options                             |
| **POST** | `/quizzes/{id}/play` | Jouer à un quizz en soumettant les numéros des réponses sous forme clé/valeur      |
| **DELETE** | `/quizzes/{id}` | SUpprimer un quizz                                                                 |