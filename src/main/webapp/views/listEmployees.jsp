<%--@elvariable id="employeesList" type="myapp.service.implementations.EmployeesService"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<%@ include file="/fragments/bodyHeader.jsp" %>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <%@ include file="/fragments/infoPanel.jsp" %>
    <div class=" w3-card-4">
        <div class="w3-container w3-pale-green w3-padding">
            <h3>Список сотрудников</h3>
        </div>
        <c:if test="${!empty employeesList}">
            <table class="w3-table-all">
                <thead>
                <tr class="w3-light-gray">
                    <th>Сотрудник</th>
                    <th>Отдел</th>
                    <th>Начало работы</th>
                    <th>Зарплата</th>
                    <th colspan="2"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employeesList}" var="i">
                    <tr class="w3-hover-sand">
                        <form method="get" action="/main/employees/edit">
                            <td>${i.getName()}</td>
                            <td>${i.getDepartmentName()}</td>
                            <td>${i.getStartDate()}</td>
                            <td>${i.getSalary()}</td>
                            <td style="float: right">
                                <button class="w3-btn w3-round-large w3-hover-red w3-opacity w3-border w3-border-black w3-padding-small"
                                        type="submit" formaction="/main/employees/delete">Удалить
                                </button>
                            </td>
                            <td style="float: right">
                                <button class="w3-btn w3-round-large w3-hover-teal w3-opacity w3-border w3-border-black w3-padding-small"
                                        type="submit" formaction="/main/employees/edit">Редактировать
                                </button>
                            </td>
                            <input type="hidden" name="employeeID" value="${i.getId()}">
                        </form>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </c:if>
        <c:if test="${empty employeesList}">
            <p>Список сотрудников пуст</p>
        </c:if>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
