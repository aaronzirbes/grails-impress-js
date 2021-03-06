<%@ page import="com.github.aaronzirbes.impress.js.Slide" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'slide.label', default: 'Slide')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<ckeditor:resources />
	</head>
	<body>
		<a href="#edit-slide" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="presentation" action="edit" id="${slideInstance?.presentation?.id}"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<g:if test="${prevSlideInstance}">
				<li><g:link class="edit" action="edit" id="${prevSlideInstance?.id}"><g:message code="default.button.prevSlide.label" default="Previous Slide" /></g:link></li>
				</g:if>
				<li><g:link class="show" action="show" target="_blank" controller="presentation" id="${slideInstance?.presentation?.id}"><g:message code="default.presentation.show" default="View Presentation" /></g:link></li>
				<g:if test="${nextSlideInstance}">
				<li><g:link class="edit" action="edit" id="${nextSlideInstance?.id}"><g:message code="default.button.nextSlide.label" default="Next Slide" /></g:link></li>
				</g:if>
				<g:else>
				<li><g:link class="create" controller="slide" action="create" params="['presentation.id': slideInstance?.presentation?.id]">${message(code: 'default.add.label', args: [message(code: 'slide.label', default: 'Slide')])}</g:link></li>
				</g:else>
			</ul>
		</div>
		<div id="edit-slide" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /> # ${slideInstance.sortOrder}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${slideInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${slideInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${slideInstance?.id}" />
				<g:hiddenField name="version" value="${slideInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
