<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список аудиторий</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<c:if test="${list == null}">
    Вы ввели неверные данные.
</c:if>
<c:if test="${list != null}">
    <table border="1">
        <tr>
            <th>Номер</th>
            <th>Вид</th>
            <th>Вместимость</th>
        </tr>
        <c:forEach items="${list}" var="classroom">
            <tr>
                <td>
                    <c:out value="${classroom.name}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${classroom.type.equals(\"flow\")}">
                            Поточная
                        </c:when>
                        <c:when test="${classroom.type.equals(\"group\")}">
                            Семинарная
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:out value="${classroom.capacity}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
