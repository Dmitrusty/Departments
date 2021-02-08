<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<%@ include file="/fragments/bodyHeader.jsp" %>

<div class="mycard-cc w3-card-4 w3-round padding-bottom">
    <%@ include file="/fragments/infoPanel.jsp" %>
    <div class="w3-container w3-orange">
        <h3>Измените данные отдела ${department.name}</h3>
    </div>
    <div>
        <form method="post" class="w3-container">
            <br/>
            <table class="big w3-right-align">
                <tr>
                    <td><label for="nam">Новое название отдела:*</label></td>
                    <td><input id="nam" type="text" name="name" value="${department.name}" size="35" maxlength="35"
                               required/></td>
                </tr>
            </table>
            <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
            <input type="submit" class="w3-left w3-hover-orange" value="Изменить">
        </form>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
