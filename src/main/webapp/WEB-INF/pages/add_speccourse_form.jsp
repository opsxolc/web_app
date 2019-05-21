<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Добавить спецкурс</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<form:form method="post" modelAttribute="sclass" action="add_speccourse">
  <form:input path="pairNum" value="${sclass.pairNum}" hidden="true"/>
  <form:input path="wday" value="${sclass.wday}" hidden="true"/>
  <b>Курс</b><br>
  <form:select path="tcId">
    <c:forEach items="${tcs}" var="tc">
      <form:option value="${tc.tcId}">
        ${tc.courseByCourseId.courseName}<br>
        ${tc.teacherByTeacherId.lastname} ${tc.teacherByTeacherId.firstname} ${tc.teacherByTeacherId.patronymic}
      </form:option>
    </c:forEach>
  </form:select><br>
  <b>Аудитория</b><br>
  <form:select path="classroomId">
    <c:forEach items="${classrooms}" var="classroom">
      <form:option value="${classroom.classroomId}">${classroom.name}</form:option>
    </c:forEach>
  </form:select><br>
  <form:button value="add_speccourse">Добавить</form:button>
</form:form>
</body>
</html>
