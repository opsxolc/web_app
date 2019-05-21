<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Просмотр расписания</title>
</head>
<body>
<a href="/h/">На главную</a><br>

<table>
    <tr>
        <th colspan="2">Получить расписание</th>
    </tr>
    <tr>
        <td colspan="2">
            <form:form id="formScheduleStud" modelAttribute="scheduleInput" method="post" action="schedule_show_stud">
                Фамилия<br>
                <form:input path="lastname"/><br>
                Имя<br>
                <form:input path="firstname"/><br>
                Отчество<br>
                <form:input path="patronymic"/><br>
        </td>
        <td valign="top">
            Номер аудитории<br>
            <form:input path="classroom"/><br>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            Дни недели (не обязательно)<br>
            <form:input path="daysStr"/><br>
        </td>
    </tr>
    <tr>
        <td align="center">
                <form:button value="schedule_show_stud">Для студента</form:button>
        </td>
        <td align="center">
                <button type="submit" value="schedule_show_teacher"
                        formaction="schedule_show_teacher">Для преподавателя</button>
        </td>
        <td align="center">
            <button type="submit" value="schedule_show_classroom"
                    formaction="schedule_show_classroom">Для аудитории</button>
            </form:form>
        </td>
    </tr>
</table>
</body>
</html>
