<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение студентов</title>
</head>
<body>
<a href="/h/">На главную</a><br>
<table border="1">
  <tr>
    <th colspan="3">
      <a href="add_stud_form">Добавить студента</a>
    </th>
  </tr>
  <tr>
    <th>ФИО</th>
    <th>Группа</th>
    <th>Действие</th>
  </tr>
  <c:forEach items="${list}" var="student">
    <tr>
      <td align="center">
        ${student.lastname} ${student.firstname} ${student.patronymic}
      </td>
      <td align="center">
        ${student.sgroupBySgroupId.groupNumber}
      </td>
      <td>
        <a href="update_stud_form?studId=${student.studentId}">Изменить</a><br>
        <a href="delete_stud?studId=${student.studentId}">Удалить</a><br>
        <a href="add_spec_stud_show?studId=${student.studentId}">Записать на спецкурс</a><br>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
