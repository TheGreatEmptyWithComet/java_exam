<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Students list</h1>
<ul>
    <c:forEach var="grade" items="${gradesList}">
        <li>${grade}</li>
    </c:forEach>
</ul>

</body>
</html>
