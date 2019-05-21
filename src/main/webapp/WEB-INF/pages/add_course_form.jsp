<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить предмет</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="course" action="add_course">
  <b>Название предмета</b><br>
  <form:input path="courseName"/><br>
  <b>Покрытие</b><br>
  <form:select path="cover">
    <form:option value="group">Группа</form:option>
    <form:option value="flow">Поток</form:option>
    <form:option value="student">Спецкурс</form:option>
  </form:select><br>
  <b>Год обучения (необязательно)</b><br>
  <form:input path="yearOfStudy" value="${null}"/><br>
  <b>Кол-во пар в неделю (необязательно)</b><br>
  <form:input path="intensity" value="${null}"/><br>
  <form:button value="add_course">Добавить</form:button>
</form:form>
</body>
</html>
