<%@ page import="java.io.File" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%
    String uniqueFileName = request.getPathInfo().substring(1);
    InputStream in = Files.newInputStream(Paths.get(request.getSession().getAttribute("path") + File.separator + uniqueFileName));
    String type = uniqueFileName.substring(uniqueFileName.indexOf('.') + 1);
    if ("jpeg".equals(type))
        response.setContentType("image/jpeg");
    else
        response.setContentType("image/png");
    ServletOutputStream outStream = response.getOutputStream();
    outStream.write(in.readAllBytes());
    outStream.close();
    in.close();
%>