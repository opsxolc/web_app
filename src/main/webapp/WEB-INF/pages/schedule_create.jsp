<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Составление расписания</title>
</head>
<body>
<c:set var="i_flow" value="0"/>
<c:set var="i_group" value="0"/>
<a href="/h/">На главную</a><br>
<c:if test="${ok == 0}">
  Введены неверные данные
</c:if>
<c:if test="${ok == 1}">
  <table border="1">
    <tr>
      <th colspan="${groups.size()}">
        Поток ${flow.flowNumber}
      </th>
    </tr>
    <tr>
      <c:forEach items="${groups}" var="group">
        <th>
          Группа ${group.groupNumber}
        </th>
      </c:forEach>
    </tr>
    <c:forEach var="day" begin="1" end="6">
      <tr>
        <th colspan="${groups.size()}">
          <c:choose>
            <c:when test="${day == 1}">Понедельник</c:when>
            <c:when test="${day == 2}">Вторник</c:when>
            <c:when test="${day == 3}">Среда</c:when>
            <c:when test="${day == 4}">Четверг</c:when>
            <c:when test="${day == 5}">Пятница</c:when>
            <c:when test="${day == 6}">Суббота</c:when>
          </c:choose>
        </th>
      </tr>
      <c:forEach var="pairNum" begin="1" end="6">
        <c:set var="no_pair" value="1"/>
        <tr>
          <c:if test="${!(i_flow < flowClasses.size() &&
                    flowClasses[i_flow].wday == day && flowClasses[i_flow].pairNumber == pairNum)}">
            <c:set var="groupNum" value="1"/>
            <c:forEach items="${groups}" var="group">
              <c:if test="${!(i_group < groupsClasses.size() &&
                            group.equals(groupsClasses[i_group].groups.toArray()[0]) &&
                            groupsClasses[i_group].wday == day && groupsClasses[i_group].pairNumber == pairNum)}">
                <td align="center">
                  <a href="add_pair_form?wday=${day}&pairNum=${pairNum}&groupId=${group.sgroupId}&flowId=${flow.flowId}">
                    Добавить семинар
                  </a><br>
                  <c:if test="${no_pair == 1 && groupNum == groups.size()}">
                    <a href="add_pair_form?wday=${day}&pairNum=${pairNum}&flowId=${flow.flowId}&groupId=0">
                      Добавить лекцию
                    </a>
                  </c:if>
                </td>
              </c:if>
              <c:if test="${i_group < groupsClasses.size() &&
                            group.equals(groupsClasses[i_group].groups.toArray()[0]) &&
                            groupsClasses[i_group].wday == day && groupsClasses[i_group].pairNumber == pairNum}">
                <td align="center">
                  <c:out value="${groupsClasses[i_group].tcByTcId.courseByCourseId.courseName}"/><br>
                  <c:out value="${groupsClasses[i_group].classroomByClassroomId.name}"/><br>
                  <a href="update_pair_form?sclassId=${groupsClasses[i_group].sclassId}
                  &groupId=${group.sgroupId}&flowId=${flow.flowId}">
                    Изменить
                  </a><br>
                  <a href="delete_pair?sclassId=${groupsClasses[i_group].sclassId}
                  &groupId=${group.sgroupId}&flowId=${flow.flowId}">
                    Удалить
                  </a>
                  <c:set var="i_group" value="${i_group + 1}"/>
                  <c:set var="no_pair" value="0"/>
                </td>
              </c:if>
              <c:set var="groupNum" value="${groupNum + 1}"/>
            </c:forEach>
          </c:if>
          <c:if test="${i_flow < flowClasses.size() &&
                    flowClasses[i_flow].wday == day && flowClasses[i_flow].pairNumber == pairNum}">
            <td colspan="${groups.size()}" align="center">
              <c:out value="${flowClasses[i_flow].tcByTcId.courseByCourseId.courseName}"/><br>
              <c:out value="${flowClasses[i_flow].classroomByClassroomId.name}"/><br>
              <a href="update_pair_form?sclassId=${flowClasses[i_flow].sclassId}&groupId=0&flowId=${flow.flowId}">
                Изменить
              </a><br>
              <a href="delete_pair?sclassId=${flowClasses[i_flow].sclassId}&groupId=0&flowId=${flow.flowId}">
                Удалить
              </a>
              <c:set var="i_flow" value="${i_flow + 1}"/>
            </td>
          </c:if>
        </tr>
      </c:forEach>

    </c:forEach>
  </table>
</c:if>
</body>
</html>
