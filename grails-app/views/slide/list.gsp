
<%@ page import="com.github.aaronzirbes.impress.js.Slide" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'slide.label', default: 'Slide')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-slide" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-slide" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="content" title="${message(code: 'slide.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="sortOrder" title="${message(code: 'slide.sortOrder.label', default: 'Sort Order')}" />
					
						<g:sortableColumn property="positionX" title="${message(code: 'slide.positionX.label', default: 'Position X')}" />
					
						<g:sortableColumn property="positionY" title="${message(code: 'slide.positionY.label', default: 'Position Y')}" />
					
						<g:sortableColumn property="positionZ" title="${message(code: 'slide.positionZ.label', default: 'Position Z')}" />
					
						<th><g:message code="slide.presentation.label" default="Presentation" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${slideInstanceList}" status="i" var="slideInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${slideInstance.id}">${fieldValue(bean: slideInstance, field: "content")}</g:link></td>
					
						<td>${fieldValue(bean: slideInstance, field: "sortOrder")}</td>
					
						<td>${fieldValue(bean: slideInstance, field: "positionX")}</td>
					
						<td>${fieldValue(bean: slideInstance, field: "positionY")}</td>
					
						<td>${fieldValue(bean: slideInstance, field: "positionZ")}</td>
					
						<td>${fieldValue(bean: slideInstance, field: "presentation")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${slideInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
