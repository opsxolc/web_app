<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить курс</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="tc" action="add_tc">
  <b>Предмет</b><br>
  <form:select path="courseId">
    <c:forEach items="${courses}" var="course">
        <form:option value="${course.courseId}">${course.courseName} ${course.cover}
          ${course.yearOfStudy}</form:option>
    </c:forEach>
  </form:select><br>
  <b>Преподаватель</b><br>
  <form:select path="teacherId">
    <c:forEach items="${teachers}" var="teacher">
      <form:option value="${teacher.teacherId}">
        ${teacher.lastname} ${teacher.firstname} ${teacher.patronymic}
      </form:option>
    </c:forEach>
  </form:select><br>
  <b>Учебный год проведения</b><br>
  <form:input path="year"/>
  <form:button value="add_tc">Добавить</form:button>
</form:form>
</body>
</html>
