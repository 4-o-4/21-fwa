<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>

<img style="width:auto;" alt="Avatar" width="200" height="200"
<c:choose>
    <c:when test="${user.images.size() != 0}">
        src="<c:url value="data:${user.images.get(user.images.size() - 1).mime};base64,${img}"/>"/>
    </c:when>
    <c:otherwise>
        src="/"/>
    </c:otherwise>
</c:choose>

<h1>Profile: ${user.firstname} ${user.lastname}</h1>
<p>Phone: ${user.phone}</p>

<form action="profile" method="post" enctype="multipart/form-data">
    <div>
        <label>
            <input type="file" name="file">
        </label>
    </div>
    <div>
        <input type="submit" value="Upload">
    </div>
</form>

<table width="700px" border="1" cellpadding="10">
    <thead bgcolor="bisque">
        <tr>
            <td>File name</td>
            <td>Size</td>
            <td>MIME</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="image" items="${user.images}">
        <tr>
            <td>
                <a href="images/<c:out value="${image.file}"/>" target="_blank"><c:out value="${image.name}"/></a>
            </td>
            <td>
                <c:set var="size" value="${image.size / 1000}"/>
                <c:choose>
                    <c:when test="${size >= 1000}">
                        <fmt:formatNumber value="${size / 1000}" pattern="0.0"/> MB
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${size}" pattern="0"/> KB
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:out value="${image.mime}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>