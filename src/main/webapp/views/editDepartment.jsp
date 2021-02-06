<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHeadBodyHeader.jsp" %>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <%@ include file="/fragments/infoPanel.jsp" %>

    <div class="w3-card-4">
        <div class="w3-container w3-orange">
            <h3>Измените данные существующего отдела:</h3>
        </div>

        <div>
            <form method="post" class="w3-container">
                <br/>
                <table class="big">
                    <tr>
                        <td><label for="nam">Название отдела:*</label></td>
                        <td><input id="nam" type="text" name="name" value="${department.name}" size="35" maxlength="35"
                                   required/></td>
                    </tr>
                </table>
                <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
                <input type="submit" class="w3-left small" value="Изменить">
            </form>
            <br/>
        </div>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
