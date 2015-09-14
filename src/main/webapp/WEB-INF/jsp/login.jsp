<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:setLocale value="en"/>
<fmt:setBundle basename="messages.properties" var="lang"/>
<h1><fmt:message key="login.form.title"/></h1>

<c:if test="${param.error ne null}">
    <p><fmt:message key="login.error.wrongpassword"/></p>
</c:if>
<c:if test="${param.logout ne null}">
    <p><fmt:message key="login.info.logout"/></p>
</c:if>


<c:url var="url" value="/login" />
<form:form modelAttribute="Account" method="POST" action="${url}"  >
    <fieldset>
        <div class="form-row">
            <label path="uri"><fmt:message key="login.form.username"/>:</label>
            <span class="input" ><form:input path="username" /></span>
        </div>
        <div class="form-row">
            <label path="description"><fmt:message key="login.form.password"/>:</label>
            <span class="input" ><form:input type="password" path="password" /></span>
        </div>
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.login"/>" /></div>
        </div>
    </fieldset>
</form:form>