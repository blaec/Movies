<%@ attribute name="isChecked" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="value" required="true" rtexprvalue="true" %>
<%@ attribute name="caption" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-check form-check-inline">
    <input class="form-check-input"
           type="radio"
           name="card-size"
           <c:if test="${isChecked}">checked</c:if>
           id="${id}"
           value="${value}">
    <label class="form-check-label" for="${id}">${caption}</label>
</div>