<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<%@ include file="/fragments/bodyHeader.jsp" %>

<div class="mycard-cc w3-card-4 w3-round padding-bottom">
    <%@ include file="/fragments/infoPanel.jsp" %>

    <div class="w3-container w3-green">
        <h3>Введите данные нового сотрудника:</h3>
    </div>

    <div>
        <form method="post" class="w3-container">
            <br/>
            <table class="big">
                <tr>
                    <td class="w3-right-align"><label for="fio">Полное имя (ФИО):*</label></td>
                    <td><input id="fio" type="text" name="name" size="35" maxlength="35" required/></td>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="deptName">Название отдела:*</label></td>
                    <td><input id="deptName" type="text" name="departmentName" size="35" maxlength="35" required/>
                    </td>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="sal">Размер зарплаты:*</label></td>
                    <td><input id="sal" type="number" name="salary" step="1" min="1" max="100000" required/><br/>
                    </td>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="dat">Дата начала работы:*</label></td>
                    <td><input id="dat" type="date" name="startDate" required/></td>
                </tr>
            </table>
            <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
            <input type="submit" class="w3-left w3-hover-green" value="Добавить">
            <%--            <input type="submit" class="w3-left" style="width:20ch; margin-left: 1ch"--%>
            <%--                   value="Список сотрудников" formaction="/main/employees/list" formmethod="get">--%>
            <button style="width:20ch; margin-left: 1ch" class="w3-hover-pale-green" onclick="location.href='/main/employees/list'">Список
                сотрудников
            </button>
            <button style="width:20ch; margin-left: 1ch" class="w3-hover-pale-yellow" onclick="location.href='/main/departments/list'">Список
                отделов
            </button>
        </form>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
