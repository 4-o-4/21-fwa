<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>

<img style="height:auto;" alt="Avatar" width="200" height="200" src="...">

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

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>