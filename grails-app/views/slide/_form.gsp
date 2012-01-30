<%@ page import="com.github.aaronzirbes.impress.js.Slide" %>

<div class="${hasErrors(bean: slideInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="slide.title.label" default="Title" />
	</label><br/>
	<g:textField size="30" name="title" value="${slideInstance?.title}"/>
</div>

<div class="${hasErrors(bean: slideInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="slide.content.label" default="Content" />
	</label><br/>
	<g:textArea style="width:640px;height:480px;" name="content" value="${slideInstance?.content}"/>
</div>

<h:hiddenField name="presentation.id" value="${slideInstance?.presentation?.id}" />

<div class="fieldcontain ${hasErrors(bean: slideInstance, field: 'scale', 'error')} required">
	<label for="scale">
		<g:message code="slide.scale.label" default="Scale" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="scale" required="" value="${fieldValue(bean: slideInstance, field: 'scale')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: slideInstance, field: 'positionX', 'error')} required">
	<label><g:message code="slide.scale.label" default="Location" /></label>
	X:
	<g:field type="number" style="width:5em;" name="positionX" required="" value="${slideInstance.positionX}"/>
	Y:
	<g:field type="number" style="width:5em;" name="positionY" required="" value="${slideInstance.positionY}"/>
	Z:
	<g:field type="number" style="width:5em;" name="positionZ" required="" value="${slideInstance.positionZ}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: slideInstance, field: 'rotationX', 'error')} required">
	<label><g:message code="slide.rotation.label" default="Rotation" /></label>
	X:
	<g:field type="number" style="width:4em;" name="rotationX" required="" value="${slideInstance.rotationX}"/>
	Y:
	<g:field type="number" style="width:4em;" name="rotationY" required="" value="${slideInstance.rotationY}"/>
	Z:
	<g:field type="number" style="width:4em;" name="rotationZ" required="" value="${slideInstance.rotationZ}"/>
</div>
