<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHeadBodyHeader.jsp" %>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <%@ include file="/fragments/infoPanel.jsp" %>

    <div class="w3-card-4">
        <div class="w3-container w3-pale-yellow">
            <h3>Список отделов</h3>
        </div>

        <c:if test="${!empty departmentsList}">
            <table class="w3-table-all">
                <tbody>
                <c:forEach items="${departmentsList}" var="i">
                    <tr class="w3-hover-sand">
                        <form method="get" action="/main/departments/edit">
                            <td>${i.getName()}</td>
                            <td style="float: right">
                                <button class="w3-btn w3-round-large w3-hover-green w3-opacity w3-border w3-border-black w3-padding-small" type="submit" formaction="/main/employees/list">Сотрудники</button>
                            </td>
                            <td style="float: right">
                                <button class="w3-btn w3-round-large w3-hover-red w3-opacity w3-border w3-border-black w3-padding-small" type="submit" formaction="/main/departments/delete">Удалить</button>
                            </td>
                            <td style="float: right">
                                <button class="w3-btn w3-round-large w3-hover-yellow w3-opacity w3-border w3-border-black w3-padding-small" type="submit" formaction="/main/departments/edit">Редактировать</button>
                            </td>
                            <input type="hidden" name="departmentID" value="${i.getId()}">
                        </form>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </c:if>
        <c:if test="${empty departmentsList}">
            <p>Список отделов пуст</p>
        </c:if>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
