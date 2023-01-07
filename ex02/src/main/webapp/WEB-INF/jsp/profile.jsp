<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>

<div class="columns">
    <div>
        <img style="width:auto;" alt="Avatar" width="200" height="200"
        <c:choose>
        <c:when test="${user.images.size() != 0}">
             src="<c:url value="data:${user.images.get(user.images.size() - 1).mime};base64,${img}"/>"/>
        </c:when>
        <c:otherwise>
            src="/"/>
        </c:otherwise>
        </c:choose>
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
    </div>
    <div>
        <h1 style="margin-top: 0">Profile: ${user.firstname} ${user.lastname}</h1>
        <p>Phone: ${user.phone}</p>
        <table width="700px" border="1" cellpadding="10">
            <thead bgcolor="bisque">
            <tr>
                <td style="width: 60%">Date</td>
                <td style="width: 20%">Time</td>
                <td style="width: 20%">IP</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="info" items="${user.info}">
                <tr>
                    <td>
                        <fmt:formatDate value="${info.date}" pattern="MMMM dd, yyyy"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${info.date}" pattern="HH:mm"/>
                    </td>
                    <td>
                        <c:out value="${info.ip}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table width="700px" border="1" cellpadding="10">
            <thead bgcolor="bisque">
            <tr>
                <td style="width: 60%">File name</td>
                <td style="width: 20%">Size</td>
                <td style="width: 20%">MIME</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="image" items="${user.images}">
                <tr>
                    <td>
                        <a href="images/<c:out value="${image.file}"/>" target="_blank"><c:out
                                value="${image.name}"/></a>
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
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>