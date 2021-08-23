<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="g" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gift Certificates</title>
</head>
<body>
<div align="center">
<h1>Gift Certificates</h1>
<h3><a href="${pageContext.request.contextPath}/new">New Gift Certificate</a></h3>
<table cellpadding="5" border="1">
                <th>No</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Duration</th>
                <th>Action</th>

                <g:forEach var="giftCertificate" items="${giftCertificateList}" varStatus="status">
                <tr>
                    <td>${giftCertificate.id}</td>
                    <td>${giftCertificate.name}</td>
                    <td>${giftCertificate.description}</td>
                    <td>${giftCertificate.price}</td>
                    <td>${giftCertificate.duration}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/update?value=${giftCertificate.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/${giftCertificate.id}">Delete</a>
                    </td>

                </tr>
                </g:forEach>
            </table>
</div>
</body>
</html>