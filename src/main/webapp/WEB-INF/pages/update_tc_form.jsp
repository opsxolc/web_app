<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменение курса</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="tc" action="update_tc">
  <form:hidden path="tcId"/>
  <b>Предмет</b><br>
  <form:select path="courseId">
    <c:forEach items="${courses}" var="course">
      <c:if test="${course.courseId == tc.courseId}">
        <form:option value="${course.courseId}" selected="true">
          ${course.courseName} ${course.cover} ${course.yearOfStudy}
        </form:option>
      </c:if>
      <c:if test="${course.courseId != tc.courseId}">
        <form:option value="${course.courseId}">${course.courseName} ${course.cover}
          ${course.yearOfStudy}</form:option>
      </c:if>
    </c:forEach>
  </form:select><br>
  <b>Преподаватель</b><br>
  <form:select path="teacherId">
    <c:forEach items="${teachers}" var="teacher">
      <c:if test="${teacher.teacherId == tc.teacherId}">
        <form:option value="${teacher.teacherId}" selected="true">
          ${teacher.lastname} ${teacher.firstname} ${teacher.patronymic}
        </form:option>
      </c:if>
      <c:if test="${teacher.teacherId != tc.teacherId}">
        <form:option value="${teacher.teacherId}">
          ${teacher.lastname} ${teacher.firstname} ${teacher.patronymic}
        </form:option>
      </c:if>
    </c:forEach>
  </form:select><br>
  <b>Учебный год проведения</b><br>
  <form:input path="year" value="${tc.year}"/>
  <form:button value="update_tc">Изменить</form:button>
</form:form>

</body>
</html>
