<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<%@ include file="/fragments/bodyHeader.jsp" %>

<div class="mycard-cc w3-card-4 w3-round padding-bottom">
    <%@ include file="/fragments/infoPanel.jsp" %>
        <div class="w3-container w3-teal">
            <h3>Измените данные сотрудника ${employee.name}</h3>
        </div>
        <div>
            <form method="post" class="w3-container">
                <br/>
                <table class="big">
                    <tr>
                        <td><label for="fio">Полное имя (ФИО):*</label></td>
                        <td><input id="fio" type="text" name="newName" value="${employee.name}"
                                   size="35" maxlength="35" required/></td>
                    </tr>
                    <tr>
                        <td><label for="deptName">Название отдела:*</label></td>
                        <td><input id="deptName" type="text" name="newDepartmentName" value="${departmentName}"
                                   size="35" maxlength="35" required/></td>
                    </tr>
                    <tr>
                        <td><label for="sal">Размер зарплаты:*</label></td>
                        <td><input id="sal" type="number" name="newSalary" value="${employee.salary}"
                                   step="1" min="0" max="100000" required/><br/></td>
                    </tr>
                    <tr>
                        <td><label for="dat">Дата начала работы:*</label></td>
                        <td><input id="dat" type="date" name="newStartDate" value="${employee.startDate}"
                                   required/></td>
                    </tr>
                </table>
                <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
                <input type="submit" class="w3-left w3-btn w3-round-large w3-hover-teal w3-border w3-border-black w3-padding-small w3-margin-right"
                       value="Изменить">


                <c:if test="${departmentName != null}">
                    <a class="w3-left w3-btn w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small w3-margin-right"
                       href="/main/employees/list?departmentName=${departmentName}">Список сотрудников
                        отдела ${departmentName}</a>
                </c:if>
                <c:if test="${departmentName == null}">
                    <a class="w3-left w3-btn w3-disabled w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small w3-margin-right"
                       href="#">Список сотрудников отдела ...</a>
                </c:if>


                <input type="hidden" name="employeeID" value="${employee.getId()}">
            </form>
        </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
