<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>New/Edit Certificate</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Gift Certificate</h1>

    <form:form action="save" method="post" modelAttribute="giftCertificateDto">
        <table cellpadding="5">
            <form:hidden path="id" />
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
