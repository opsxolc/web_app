<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить пару</title>
</head>
<body>
<input type="button" onclick="history.back();" value="Назад"/><br>
<c:if test="${groupId != 0}">
  <b>Добавление семинара</b>
</c:if>
<c:if test="${groupId == 0}">
  <b>Добавление лекции</b>
</c:if>
<form:form method="post" modelAttribute="sclassCreateInput" action="add_pair?groupId=${groupId}&flowId=${flowId}">
  <form:input path="pairNum" value="${sclassCreateInput.pairNum}" hidden="true"/>
  <form:input path="wday" value="${sclassCreateInput.wday}" hidden="true"/>
  <form:select path="tcId">
    <form:option value="0">Выберите курс</form:option>
    <c:forEach items="${tcs}" var="tc">
      <form:option value="${tc.tcId}">
        ${tc.courseByCourseId.courseName}<br>
        ${tc.teacherByTeacherId.lastname} ${tc.teacherByTeacherId.firstname} ${tc.teacherByTeacherId.patronymic}
      </form:option>
    </c:forEach>
  </form:select><br>
  <form:select path="classroomId">
    <form:option value="0">Выберите аудиторию</form:option>
    <c:forEach items="${classrooms}" var="classroom">
      <form:option value="${classroom.classroomId}">${classroom.name}</form:option>
    </c:forEach>
  </form:select><br>
  <form:button value="add_pair">Добавить</form:button>
</form:form>

</body>
</html>
