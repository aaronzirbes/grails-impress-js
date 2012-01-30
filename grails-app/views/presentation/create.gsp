<%@ page import="com.github.aaronzirbes.impress.js.Presentation" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'presentation.label', default: 'Presentation')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-presentation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-presentation" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${presentationInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${presentationInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'title', 'error')} ">
						<label for="title">
							<g:message code="presentation.title.label" default="Title" />

						</label>
						<g:textField name="title" size="30" maxSize="255" value="${presentationInstance?.title}"/>
					</div>

					<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'description', 'error')} ">
						<label for="description">
							<g:message code="presentation.description.label" default="Description" />

						</label>
						<g:textField name="description" size="80" maxSize="255" value="${presentationInstance?.description}"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
