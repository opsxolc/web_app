<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Изменить пару</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<c:if test="${groupId != 0}">
  <b>Добавление семинара</b>
</c:if>
<c:if test="${groupId == 0}">
  <b>Добавление лекции</b>
</c:if>
<form:form method="post" modelAttribute="sclassInput" action="update_pair?flowId=${flowId}&groupId=${groupId}">
  <form:input path="sclassId" value="${sclassInput.sclassId}" hidden="true"/>
  <b>Курс</b><br>
  <form:select path="tcId">
    <form:option selecterd="true" value="${tcSel.tcId}">
      ${tcSel.courseByCourseId.courseName}<br>
      ${tcSel.teacherByTeacherId.lastname} ${tcSel.teacherByTeacherId.firstname} ${tcSel.teacherByTeacherId.patronymic}
    </form:option>
    <c:forEach items="${tcs}" var="tc">
      <form:option value="${tc.tcId}">
        ${tc.courseByCourseId.courseName}<br>
        ${tc.teacherByTeacherId.lastname} ${tc.teacherByTeacherId.firstname} ${tc.teacherByTeacherId.patronymic}
      </form:option>
    </c:forEach>
  </form:select><br>
  <b>Аудитория</b><br>
  <form:select path="classroomId">
    <form:option selected="true" value="${classroomSel.classroomId}">${classroomSel.name}</form:option>
    <c:forEach items="${classrooms}" var="classroom">
      <form:option value="${classroom.classroomId}">${classroom.name}</form:option>
    </c:forEach>
  </form:select><br>
  <form:button value="update_pair">Изменить</form:button>
</form:form>
</body>
</html>
