<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение студента</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="udInput" action="update_stud">
  <form:input path="studentId" value="${udInput.studentId}" hidden="true"/>
  <b>Фамилия</b><br>
  <form:input path="lastname" value="${udInput.lastname}"/><br>
  <b>Имя</b><br>
  <form:input path="firstname" value="${udInput.firstname}"/><br>
  <b>Отчество</b><br>
  <form:input path="patronymic" value="${udInput.patronymic}"/><br>
  <b>Группа</b><br>
  <form:select path="groupId">
    <c:forEach items="${groups}" var="group">
      <c:if test="${group.sgroupId == udInput.groupId}">
        <form:option value="${group.sgroupId}" selected="true">${group.groupNumber}</form:option>
      </c:if>
      <c:if test="${group.sgroupId != udInput.groupId}">
        <form:option value="${group.sgroupId}">${group.groupNumber}</form:option>
      </c:if>
    </c:forEach>
  </form:select><br>
  <form:button value="update_stud">Изменить</form:button>
</form:form>
</body>
</html>
