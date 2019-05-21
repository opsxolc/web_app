<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Изменение курсов</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<table>
  <tr>
    <td>
      <b><i>Добавить спецкурс</i></b><br>
      <form:form method="post" modelAttribute="sclass" action="add_speccourse_form">
        <b>День недели</b><br>
        <form:select path="wday">
          <form:option value="1">Понедельник</form:option>
          <form:option value="2">Вторник</form:option>
          <form:option value="3">Среда</form:option>
          <form:option value="4">Четверг</form:option>
          <form:option value="5">Пятница</form:option>
          <form:option value="6">Суббота</form:option>
        </form:select><br>
        <b>Номер пары</b><br>
        <form:select path="pairNum">
          <c:forEach var="pairNum" begin="1" end="6">
            <form:option value="${pairNum}">${pairNum}</form:option>
          </c:forEach>
        </form:select><br>
        <form:button value="add_speccourse_form">Перейти к форме</form:button>
      </form:form>
    </td>
  </tr>
  <tr>
    <td valign="top">
      <table border="1">
        <tr>
          <th colspan="4">
            <a href="add_course_form">Добавить предмет</a>
          </th>
        </tr>
        <tr>
          <th>Название предмета</th>
          <th>Покрытие</th>
          <th>Год обучения</th>
          <th>Действие</th>
        </tr>
        <c:forEach items="${courses}" var="course">
          <tr>
            <td>${course.courseName}</td>
            <td align="center">
              <c:choose>
                <c:when test="${course.cover.equals(\"flow\")}">
                  Поток
                </c:when>
                <c:when test="${course.cover.equals(\"group\")}">
                  Группа
                </c:when>
                <c:when test="${course.cover.equals(\"student\")}">
                  Спецкурс
                </c:when>
              </c:choose>
            </td>
            <td align="center">
              <c:if test="${course.yearOfStudy != null}">
                ${course.yearOfStudy}
              </c:if>
              <c:if test="${course.yearOfStudy == null}">
                -
              </c:if>
            </td>
            <td>
              <a href="update_course_form?courseId=${course.courseId}">Изменить</a><br>
              <a href="delete_course?courseId=${course.courseId}">Удалить</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </td>
    <td valign="top">
      <table border="1">
        <tr>
          <th colspan="5">
            <a href="add_tc_form">Добавить курс</a>
          </th>
        </tr>
        <tr>
          <th>Название предмета</th>
          <th>Покрытие</th>
          <th>Преподаватель</th>
          <th>Учебный год проведения</th>
          <th>Действие</th>
        </tr>
        <c:forEach items="${tcs}" var="tc">
          <tr>
            <td>${tc.courseByCourseId.courseName}</td>
            <td align="center">
              <c:choose>
                <c:when test="${tc.courseByCourseId.cover.equals(\"flow\")}">
                  Поток
                </c:when>
                <c:when test="${tc.courseByCourseId.cover.equals(\"group\")}">
                  Группа
                </c:when>
                <c:when test="${tc.courseByCourseId.cover.equals(\"student\")}">
                  Спецкурс
                </c:when>
              </c:choose>
            </td>
            <td>
              ${tc.teacherByTeacherId.lastname} ${tc.teacherByTeacherId.firstname}
                  ${tc.teacherByTeacherId.patronymic}
            </td>
            <td align="center">
              ${tc.year}-${tc.year+1}
            </td>
            <td>
              <a href="update_tc_form?tcId=${tc.tcId}">Изменить</a><br>
              <a href="delete_tc?tcId=${tc.tcId}">Удалить</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
