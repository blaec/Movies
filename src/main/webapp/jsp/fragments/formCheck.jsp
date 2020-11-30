<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-check form-check-inline">
    <input class="form-check-input"
           type="radio"
           name="card-size"
           <c:if test="${param.isChecked}">checked</c:if>
           id="${param.id}"
           value="${param.value}">
    <label class="form-check-label" for="${param.id}">${param.caption}</label>
</div>