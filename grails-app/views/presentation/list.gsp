
<%@ page import="com.github.aaronzirbes.impress.js.Presentation" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'presentation.label', default: 'Presentation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-presentation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-presentation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th><g:message code="presentation.title.label" default="Preview" /></th>
						<g:sortableColumn property="title" title="${message(code: 'presentation.title.label', default: 'Title')}" />
						<g:sortableColumn property="lastUpdated" title="${message(code: 'presentation.lastUpdated.label', default: 'Last Updated')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${presentationInstanceList}" status="i" var="presentationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td class="slide-preview">${presentationInstance?.previewSlide?.content}</td>
						<td>
							<h2>${fieldValue(bean: presentationInstance, field: "title")}</h2>
							<p>${fieldValue(bean: presentationInstance, field: "description")}</p>
						</td>
						<td>
							<dl>
								<dt>${message(code: 'presentation.author.label', default: 'Author')}</dt>
								<dd>${fieldValue(bean: presentationInstance, field: "author")}</dd>
								<dt>${message(code: 'presentation.dateCreated.label', default: 'Created')}</dt>
								<dd>${fieldValue(bean: presentationInstance, field: "dateCreated")}</dd>
								<dt>${message(code: 'presentation.dateCreated.label', default: 'Updated')}</dt>
								<dd>${fieldValue(bean: presentationInstance, field: "lastUpdated")}</dd>
							</dl>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<fieldset class="buttons">
							<g:link class="show" action="show" target="_blank" id="${presentationInstance?.id}"><g:message code="default.button.show.label" default="Show" /></g:link>
							<g:link class="edit" action="edit" id="${presentationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							</fieldset>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${presentationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
