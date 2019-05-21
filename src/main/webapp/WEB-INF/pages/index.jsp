<%@ page isELIgnored="false" %>
<%@ page language ="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная</title>
</head>
<body>

<a href="/h/schedule_form">Просмотр расписания</a>
<a href="/h/update_stud_show">Изменение студентов</a>
<a href="/h/update_teacher_show">Изменение преподавателей</a>
<a href="/h/update_courses_show">Изменение курсов</a>

<table>
    <tr>
        <th>Получение списков студентов</th>
        <th>Получение списков преподавателей</th>
        <th>Получение списков аудиторий</th>
    </tr>
    <tr>
        <td valign="top">
            <form:form modelAttribute="group" method="post" action="stud_show">
                <b>По группе</b><br>
                <label>Номер группы</label>
                <form:input path="groupNumber"/><br>
                <form:button value="stud_show">Показать</form:button>
            </form:form>
            <form:form modelAttribute="flow" method="post" action="stud_show">
                <b>По потоку</b><br>
                <label>Номер потока</label>
                <form:input path="flowNumber"/><br>
                <label>Год обучения</label>
                <form:input path="yearOfStudy"/><br>
                <form:button value="stud_show">Показать</form:button>
            </form:form>
            <a href="/h/stud_show_all">Показать полный список</a>
        </td>
        <td valign="top">
            <form:form modelAttribute="course" method="post" action="teacher_show">
                <b>По проводимому курсу</b><br>
                <form:select path="courseName">
                    <form:option value="-">Выберите курс</form:option>
                    <c:forEach items="${courseList}" var="courseName">
                        <form:option value="${courseName}" title="${courseName}"/>
                    </c:forEach>
                </form:select>
                <form:button value="teacher_show">Показать</form:button>
            </form:form>
            <a href="/h/teacher_show_all">Показать полный список</a>
        </td>
        <td valign="top">
            <form:form modelAttribute="interval" method="post" action="classroom_show">
                Выберите день недели:<br>
                <form:select path="day">
                    <form:option value="0">День недели</form:option>
                    <form:option value="1">Понедельник</form:option>
                    <form:option value="2">Вторник</form:option>
                    <form:option value="3">Среда</form:option>
                    <form:option value="4">Четверг</form:option>
                    <form:option value="5">Пятница</form:option>
                    <form:option value="6">Суббота</form:option>
                </form:select><br>
                Введите номера пар через запятую:<br>
                <form:input path="pairNumsStr"/>
                <form:button value="classroom_show">Показать</form:button>
            </form:form>
        </td>
    </tr>
    <tr>
        <th>Составление расписания</th>
    </tr>
    <tr>
        <td>
            <form:form modelAttribute="flow" method="post" action="schedule_create">
                Введите номер курса<br>
                <form:input path="yearOfStudy"/><br>
                Введите номер потока<br>
                <form:input path="flowNumber"/><br>
                <form:button value="schedule_create">Выбрать</form:button>
            </form:form>
        </td>
    </tr>

</table>
</body>
</html>
