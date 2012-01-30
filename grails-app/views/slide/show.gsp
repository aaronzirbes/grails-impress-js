<%@ page import="com.github.aaronzirbes.impress.js.Slide" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'slide.label', default: 'Slide')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-slide" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-slide" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list slide">
			
				<g:if test="${slideInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="slide.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${slideInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.sortOrder}">
				<li class="fieldcontain">
					<span id="sortOrder-label" class="property-label"><g:message code="slide.sortOrder.label" default="Sort Order" /></span>
					
						<span class="property-value" aria-labelledby="sortOrder-label"><g:fieldValue bean="${slideInstance}" field="sortOrder"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.positionX}">
				<li class="fieldcontain">
					<span id="positionX-label" class="property-label"><g:message code="slide.positionX.label" default="Position X" /></span>
					
						<span class="property-value" aria-labelledby="positionX-label"><g:fieldValue bean="${slideInstance}" field="positionX"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.positionY}">
				<li class="fieldcontain">
					<span id="positionY-label" class="property-label"><g:message code="slide.positionY.label" default="Position Y" /></span>
					
						<span class="property-value" aria-labelledby="positionY-label"><g:fieldValue bean="${slideInstance}" field="positionY"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.positionZ}">
				<li class="fieldcontain">
					<span id="positionZ-label" class="property-label"><g:message code="slide.positionZ.label" default="Position Z" /></span>
					
						<span class="property-value" aria-labelledby="positionZ-label"><g:fieldValue bean="${slideInstance}" field="positionZ"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.presentation}">
				<li class="fieldcontain">
					<span id="presentation-label" class="property-label"><g:message code="slide.presentation.label" default="Presentation" /></span>
					
						<span class="property-value" aria-labelledby="presentation-label"><g:link controller="presentation" action="show" id="${slideInstance?.presentation?.id}">${slideInstance?.presentation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.scale}">
				<li class="fieldcontain">
					<span id="scale-label" class="property-label"><g:message code="slide.scale.label" default="Scale" /></span>
					
						<span class="property-value" aria-labelledby="scale-label"><g:fieldValue bean="${slideInstance}" field="scale"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${slideInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="slide.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${slideInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${slideInstance?.id}" />
					<g:link class="edit" action="edit" id="${slideInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
