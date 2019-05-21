<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список студентов</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<c:set value="${list[0].sgroupBySgroupId.flowByFlowId.flowNumber}" var="flowNumber"/>
<c:set var="yearOfStudy" value="${list[0].sgroupBySgroupId.flowByFlowId.yearOfStudy}"/>
<c:if test="${list == null}">
    Введены неверные данные
</c:if>
<c:if test="${list != null}">
    <table>
        <c:if test="${!(list.size() == 0)}">
            <tr>
                <td colspan="2">
                    Год обучения <c:out value="${yearOfStudy}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Поток <c:out value="${flowNumber}"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${list.size() == 0}">
            <tr>
                <td>
                    Список пуст
                </td>
            </tr>
        </c:if>
        <c:forEach var="student" items="${list}">
            <c:if test="${student.sgroupBySgroupId.flowByFlowId.yearOfStudy != yearOfStudy}">
                <tr>
                    <td colspan="2">
                        <c:set value="${student.sgroupBySgroupId.flowByFlowId.yearOfStudy}" var="yearOfStudy"/>
                        <c:set value="${0}" var="flowNumber"/>
                        Год обучения <c:out value="${yearOfStudy}"/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${student.sgroupBySgroupId.flowByFlowId.flowNumber != flowNumber}">
                <tr>
                    <td colspan="2">
                        <c:set value="${student.sgroupBySgroupId.flowByFlowId.flowNumber}" var="flowNumber"/>
                        Поток <c:out value="${flowNumber}"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <c:out value="${student.lastname}"/>
                    <c:out value="${student.firstname}"/>
                    <c:out value="${student.patronymic}"/>
                </td>
                <td>
                    <c:out value="${student.sgroupBySgroupId.groupNumber}"/>
                </td>
            </tr>
        </c:forEach>

    </table>
</c:if>
</body>
</html>
