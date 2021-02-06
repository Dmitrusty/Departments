<%@page pageEncoding="UTF-8" %>
<div class="w3-card-4">
    <div class="w3-container">
        <c:if test="${message != null}">
            <div class="w3-panel w3-pale-red w3-display-container w3-card-4 w3-round">
                    <span onclick="this.parentElement.style.display='none'"
                          class="w3-button w3-margin-right w3-display-right w3-round-large
                          w3-hover-red w3-border w3-border-red w3-hover-border-grey">×</span>
                <h5>${message} ${name}</h5>
            </div>
        </c:if>
    </div>
</div>