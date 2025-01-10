<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
    <title>Personnes</title>
</head>
<body>
    <main class="container">
        <h1>Voici vos gens</h1>
        <table>
            <thead>
            <tr>
                <th>Prénom</th>
                <th>Nom</th>
                <th>Âge</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${persons}" var="person">
                    <tr>
                        <td>${person.getFirstName()}</td>
                        <td>${person.getLastName()}</td>
                        <td>${person.getAge()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>
