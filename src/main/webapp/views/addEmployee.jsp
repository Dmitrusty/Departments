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
                    <td><input id="fio" type="text" name="newName" value="${employee.getName()}" size="35" maxlength="35" required/></td>
                    <c:if test="${nameBadMessage != null}">
                        <td class="small w3-panel w3-pale-red w3-display-container w3-card-4 w3-round">${nameBadMessage}</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="deptName">Название отдела:*</label></td>
                    <td><input id="deptName" type="text" name="newDepartmentName" value="${departmentName}" size="35"
                               maxlength="35" required/>
                    </td>
                    <c:if test="${departmentNameBadMessage != null}">
                        <td class="small w3-panel w3-pale-red w3-display-container w3-card-4 w3-round">${departmentNameBadMessage}</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="sal">Размер зарплаты:*</label></td>
                    <td><input id="sal" type="number" name="newSalary" value="${employee.getSalary()}" step="1" min="100" max="100000" required/><br/>
                    </td>
                    <c:if test="${salaryBadMessage != null}">
                        <td class="small w3-panel w3-pale-red w3-display-container w3-card-4 w3-round">${salaryBadMessage}</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="w3-right-align"><label for="dat">Дата начала работы:*</label></td>
                    <td><input id="dat" type="date" name="newStartDate" value="${employee.getStartDate()}" required/></td>
                    <c:if test="${startDateBadMessage != null}">
                        <td class="small w3-panel w3-pale-red w3-display-container w3-card-4 w3-round">${startDateBadMessage}</td>
                    </c:if>
                </tr>
            </table>
            <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>

            <input type="submit"
                   class="w3-left w3-btn w3-round-large w3-hover-green w3-border w3-border-black w3-padding-small w3-margin-right"
                   value="Добавить">


            <c:if test="${departmentName != null}">
                <a class="w3-left w3-btn w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small w3-margin-right"
                   href="/main/employees/list?departmentName=${departmentName}">Список сотрудников
                    отдела ${departmentName}</a>
            </c:if>
            <c:if test="${departmentName == null}">
                <a class="w3-left w3-btn w3-disabled w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small w3-margin-right"
                   href="#">Список сотрудников отдела ...</a>
            </c:if>

            <input type="hidden" name="departmentName" value="${departmentName}">
        </form>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>