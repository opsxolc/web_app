<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить преподавателя</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="teacher" action="add_teacher">
  <b>Фамилия</b><br>
  <form:input path="lastname"/><br>
  <b>Имя</b><br>
  <form:input path="firstname"/><br>
  <b>Отчество</b><br>
  <form:input path="patronymic"/><br>
  <form:button value="add_teacher">Добавить</form:button>
</form:form>
</body>
</html>
