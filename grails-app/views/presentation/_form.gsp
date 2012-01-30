<%@ page import="com.github.aaronzirbes.impress.js.Presentation" %>

<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="presentation.title.label" default="Title" />
	</label>
	<g:textField name="title" size="50" value="${presentationInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="presentation.description.label" default="Description" />
		
	</label>
	<g:textArea style="width:620px; height:64px;" name="description" value="${presentationInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'author', 'error')}">
	<label for="author">
		<g:message code="presentation.author.label" default="Author" />
	</label>
	<span id="author">${presentationInstance?.author}</span>
</div>

<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'showHint', 'error')} ">
	<label for="showHint">
		<g:message code="presentation.showHint.label" default="Show Hint" />
		
	</label>
	<g:checkBox name="showHint" value="${presentationInstance?.showHint}" />
</div>

<div class="fieldcontain ${hasErrors(bean: presentationInstance, field: 'slides', 'error')} ">
	<h2>
		<g:message code="presentation.slides.label" default="Slides" />
	</h2>
	<g:each in="${presentationInstance?.slides?.sort{ it.sortOrder }}" var="s">
	<div class="slide-preview">
		<g:link controller="slide" action="edit" id="${s.id}"><h3># ${s.sortOrder} - ${s.title}</h3></g:link>
		${s.content}
	</div>
	</g:each>
	<div class="slide-preview new-slide">
		<p>
<g:link controller="slide" action="create" params="['presentation.id': presentationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'slide.label', default: 'Slide')])}</g:link>
		</p>
	</div>

</div>

