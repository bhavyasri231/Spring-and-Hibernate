<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title> Zemoso Company Home Page </title>
    </head>
    <body>
        <h2> Zemoso Company Home Page - Yoohoo!!!</h2>
        <hr>
        <p>
            Welcome to the Zemoso company home page!
        </p>

        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout"/>
        </form:form>
    </body>

</html>