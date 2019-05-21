<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список учителей</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<c:if test="${(course != \"-\") && (course != null)}">
    <b>Список учителей по курсу<br>
        <i>${course}</i></b><br>
</c:if>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>ФИО</th>
        </tr>
        <c:forEach items="${list}" var="teacher">
            <tr>
                <td>
                    <c:out value="${teacher.lastname}"/>
                    <c:out value="${teacher.firstname}"/>
                    <c:out value="${teacher.patronymic}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${list.size() == 0}">
    Список пуст
</c:if>

</body>
</html>
