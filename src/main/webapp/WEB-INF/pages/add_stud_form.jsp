<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление студента</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="udInput" action="add_stud">
  <b>Фамилия</b><br>
  <form:input path="lastname"/><br>
  <b>Имя</b><br>
  <form:input path="firstname"/><br>
  <b>Отчество</b><br>
  <form:input path="patronymic"/><br>
  <b>Группа</b><br>
  <form:select path="groupId">
    <c:forEach items="${groups}" var="group">
      <form:option value="${group.sgroupId}">${group.groupNumber}</form:option>
    </c:forEach>
  </form:select><br>
  <form:button value="update_stud">Добавить</form:button>
</form:form>
</body>
</html>
