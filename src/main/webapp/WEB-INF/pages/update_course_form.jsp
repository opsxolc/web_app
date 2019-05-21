<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменить предмет</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="course" action="update_course">
  <form:hidden path="courseId"/>
  <b>Название предмета</b><br>
  <form:input path="courseName" value="${course.courseName}"/><br>
  <b>Покрытие</b><br>
  <form:select path="cover">
    <c:if test="${course.cover.equals(\"group\")}">
      <form:option value="flow">Поток</form:option>
      <form:option value="group" selected="true">Группа</form:option>
      <form:option value="student">Спецкурс</form:option>
    </c:if>
    <c:if test="${course.cover.equals(\"flow\")}">
      <form:option value="flow" selected="true">Поток</form:option>
      <form:option value="group">Группа</form:option>
      <form:option value="student">Спецкурс</form:option>
    </c:if>
    <c:if test="${course.cover.equals(\"student\")}">
      <form:option value="flow">Поток</form:option>
      <form:option value="group">Группа</form:option>
      <form:option value="student" selected="true">Спецкурс</form:option>
    </c:if>
  </form:select><br>
  <b>Год обучения (необязательно)</b><br>
  <form:input path="yearOfStudy" value="${course.yearOfStudy}"/><br>
  <b>Кол-во пар в неделю (необязательно)</b><br>
  <form:input path="intensity" value="${course.intensity}"/><br>
  <form:button value="update_course">Добавить</form:button>
</form:form>
</body>
</html>
