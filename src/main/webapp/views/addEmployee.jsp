<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHeadBodyHeader.jsp" %>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <%@ include file="/fragments/infoPanel.jsp" %>

    <div class="w3-card-4">
        <div class="w3-container w3-green">
            <h3>Введите данные нового сотрудника:</h3>
        </div>

        <div>
            <form method="post" class="w3-container">
                <br/>
                <table class="big">
                    <tr>
                        <td><label for="fio">Полное имя (ФИО):*</label></td>
                        <td><input id="fio" type="text" name="name" size="35" maxlength="35" required/></td>
                    </tr>
                    <tr>
                        <td><label for="deptName">Название отдела:*</label></td>
                        <td><input id="deptName" type="text" name="departmentName" size="35" maxlength="35" required/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="sal">Размер зарплаты:*</label></td>
                        <td><input id="sal" type="number" name="salary" step="1" min="1" max="100000" required/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="dat">Дата начала работы:*</label></td>
                        <td><input id="dat" type="date" name="startDate" required/></td>
                    </tr>
                </table>
                <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
                <input type="submit" class="w3-left small" value="Добавить">
            </form>
            <br/>
        </div>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
