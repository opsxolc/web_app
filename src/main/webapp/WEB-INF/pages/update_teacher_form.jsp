<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение преподавателя</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="teacher" action="update_teacher">
  <form:hidden path="teacherId"/>
  <b>Фамилия</b><br>
  <form:input path="lastname" value="${teacher.lastname}"/><br>
  <b>Имя</b><br>
  <form:input path="firstname" value="${teacher.firstname}"/><br>
  <b>Отчество</b><br>
  <form:input path="patronymic" value="${teacher.patronymic}"/><br>
  <form:button value="update_teacher">Изменить</form:button>
</form:form>
</body>
</html>
