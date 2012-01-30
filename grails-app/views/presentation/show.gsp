<%@ page import="com.github.aaronzirbes.impress.js.Presentation" %>
<html>
	<head>
		<meta name="layout" content="impress"/>
		<title>${presentationInstance.title}</title>
		<meta name="description" content="${presentationInstance.description}">
		<meta name="author" content="${presentationInstance.author}" />
	</head>
	<body>
		<div id="impress" class="impress-not-supported">
			<div class="fallback-message">
				<p>Your browser <b>doesn't support the features required</b> by impress.js, so you are presented with a simplified version of this presentation.</p>
				<p>For the best experience please use the latest <b>Chrome</b> or <b>Safari</b> browser. Firefox 10 (to be released soon) will also handle it.</p>
			</div>
			<g:each var="s" in="${presentationInstance.slides.sort{it.sortOrder}}">
			<g:render template="/slide/show" bean="${s}"/></g:each>
		</div>
		<g:if test="${presentationInstance.showHint}">
		<div class="hint"><p>Use a spacebar or arrow keys to navigate</p></div>
		</g:if>
	</body>
</html>
