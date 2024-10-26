<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Students list</h1>

<form:form action="getStudentsGrades" method="post" modelAttribute="subjectFormDTO">
    <form:select path="subjectId">
        <form:options items="${subjects}" itemValue="id" itemLabel="name"/>
    </form:select>
    <input type="submit" value="select subject">
</form:form>

<c:forEach items="${students}" var="student">
    <h3>Student: ${student.firstName} ${student.lastName}</h3>
    <ul>
    <c:forEach var="grade" items="${student.grades}">
        <li>
            ${grade.date} ${grade.grade} ${grade.comment}
        </li>
    </c:forEach>
    </ul>
</c:forEach>

</body>
</html>
