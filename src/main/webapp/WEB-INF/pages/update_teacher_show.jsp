<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение преподавателей</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<table border="1">
  <tr>
    <th colspan="2">
      <a href="add_teacher_form">Добавить преподавателя</a>
    </th>
  </tr>
  <tr>
    <th>ФИО</th>
    <th>Действие</th>
  </tr>
  <c:forEach items="${list}" var="teacher">
    <tr>
      <td>${teacher.lastname} ${teacher.firstname} ${teacher.patronymic}</td>
      <td>
        <a href="update_teacher_form?teacherId=${teacher.teacherId}">Изменить</a><br>
        <a href="delete_teacher?teacherId=${teacher.teacherId}">Удалить</a>
      </td>
    </tr>
  </c:forEach>

</table>
</body>
</html>
