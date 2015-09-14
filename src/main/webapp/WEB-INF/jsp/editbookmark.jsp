<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:setLocale value="en"/>
<fmt:setBundle basename="messages.properties" var="lang"/>
<%@include file="header.jsp" %>

<h1><fmt:message key="bookmarkedit.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>

<c:url var="url" value="/ManageBookmarks/edit?bid=${bid}" />
<form:form modelAttribute="bookmarkForm" method="POST" action="${url}"  >
    <fieldset>
        <div class="form-row">
            <label path="uri"><fmt:message key="bookmark.form.uri"/>:</label>
            <span class="input" value=${bookmarkForm.uri}><form:input path="uri" /></span>
        </div>
        <div class="form-row">
            <label path="description"><fmt:message key="bookmark.form.description"/>:</label>
            <span class="input" value=${bookmarkForm.description}><form:input path="description" /></span>
        </div>
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.save"/>" /></div>
        </div>
    </fieldset>
</form:form>