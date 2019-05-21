<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Запись студента на спецкурс</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
  <table>
    <tr>
      <th>
        Записать на спецкурс
      </th>
      <th>
        Отписаться от спецкурса
      </th>
    </tr>
    <tr>
      <td>
        <c:if test="${speccourses.size()==0}">
          Нет доступных спецкурсов
        </c:if>
        <c:if test="${speccourses.size()!=0}">
        <table border="1">
          <tr>
            <th>Название спецкурса</th>
            <th>Преподаватель</th>
            <th>День недели</th>
            <th>Номер пары</th>
            <th>Действие</th>
          </tr>
          <c:forEach items="${speccourses}" var="spec">
            <tr>
              <td>
                <c:out value="${spec.tcByTcId.courseByCourseId.courseName}"/>
              </td>
              <td>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.lastname}"/>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.firstname}"/>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.patronymic}"/>
              </td>
              <td>
                <c:choose>
                  <c:when test="${spec.wday == 1}">Понедельник</c:when>
                  <c:when test="${spec.wday == 2}">Вторник</c:when>
                  <c:when test="${spec.wday == 3}">Среда</c:when>
                  <c:when test="${spec.wday == 4}">Четверг</c:when>
                  <c:when test="${spec.wday == 5}">Пятница</c:when>
                  <c:when test="${spec.wday == 6}">Суббота</c:when>
                </c:choose>
              </td>
              <td>
                <c:out value="${spec.pairNumber}"/>
              </td>
              <td>
                <a href="add_spec_stud?studId=${studId}&specId=${spec.sclassId}">Выбрать</a>
              </td>
            </tr>
          </c:forEach>
        </table>
        </c:if>
      </td>
      <td>
        <c:if test="${speccoursesStud.size() == 0}">
          Нет доступных спецкурсов
        </c:if>
        <c:if test="${speccoursesStud.size() != 0}">
        <table border="1">
          <tr>
            <th>Название спецкурса</th>
            <th>Преподаватель</th>
            <th>День недели</th>
            <th>Номер пары</th>
            <th>Действие</th>
          </tr>
          <c:forEach items="${speccoursesStud}" var="spec">
            <tr>
              <td>
                <c:out value="${spec.tcByTcId.courseByCourseId.courseName}"/>
              </td>
              <td>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.lastname}"/>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.firstname}"/>
                <c:out value="${spec.tcByTcId.teacherByTeacherId.patronymic}"/>
              </td>
              <td>
                <c:choose>
                  <c:when test="${spec.wday == 1}">Понедельник</c:when>
                  <c:when test="${spec.wday == 2}">Вторник</c:when>
                  <c:when test="${spec.wday == 3}">Среда</c:when>
                  <c:when test="${spec.wday == 4}">Четверг</c:when>
                  <c:when test="${spec.wday == 5}">Пятница</c:when>
                  <c:when test="${spec.wday == 6}">Суббота</c:when>
                </c:choose>
              </td>
              <td>
                <c:out value="${spec.pairNumber}"/>
              </td>
              <td>
                <a href="delete_spec_stud?studId=${studId}&specId=${spec.sclassId}">Отписаться</a>
              </td>
            </tr>
          </c:forEach>
        </table>
        </c:if>
      </td>
    </tr>
  </table>

</body>
</html>
