<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <jsp:useBean id="genres" scope="request" type="java.util.Set<java.lang.String>"/>
    <c:forEach items="${genres}" var="genre">
        <jsp:useBean id="genre" type="java.lang.String"/>
        <p style="color: white">${genre}</p>
    </c:forEach>

</body>
<script type="text/javascript" src="js/search.js" defer></script>
</html>
