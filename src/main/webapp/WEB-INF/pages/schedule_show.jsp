<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Просмотр расписания</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<c:if test="${list == null}">Введены неверные данные</c:if>
<c:if test="${list != null}">
    <table border="1">
        <tr>
            <c:forEach items="${days}" var="day">
                <th>
                    <c:choose>
                        <c:when test="${day == 1}">Понедельник</c:when>
                        <c:when test="${day == 2}">Вторник</c:when>
                        <c:when test="${day == 3}">Среда</c:when>
                        <c:when test="${day == 4}">Четверг</c:when>
                        <c:when test="${day == 5}">Пятница</c:when>
                        <c:when test="${day == 6}">Суббота</c:when>
                    </c:choose>
                </th>
            </c:forEach>
        </tr>
        <c:set var="index" value="0"/>
        <c:forEach var="i" begin="1" end="6">
            <tr>
            <c:forEach items="${days}" var="day">
                <c:if test="${index >= list.size()}">
                    <td>
                        <br><br>
                    </td>
                </c:if>
                <c:if test="${index < list.size()}">
                    <c:if test="${!(i == list[index].pairNumber && day == list[index].wday)}">
                        <td>
                            <br><br>
                        </td>
                    </c:if>
                    <c:if test="${i == list[index].pairNumber && day == list[index].wday}">
                        <td align="center" valign="center">
                            ${list[index].tcByTcId.courseByCourseId.courseName}<br>
                            <i>
                            <c:if test="${list[index].tcByTcId.courseByCourseId.cover.equals(\"flow\")}">
                                Лекция
                            </c:if>
                            <c:if test="${!list[index].tcByTcId.courseByCourseId.cover.equals(\"flow\")}">
                                Семинар
                            </c:if>
                            </i><br>
                            <c:out value="${list[index].classroomByClassroomId.name}"/>
                        </td>
                        <c:set var="index" value="${index + 1}"/>
                    </c:if>
                </c:if>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>