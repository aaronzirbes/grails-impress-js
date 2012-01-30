package com.github.aaronzirbes.impress.js

import com.github.aaronzirbes.idm.User

class Presentation {

	String title
	String description
	Boolean publiclyViewable = false
	Boolean showHint = true
	Slide previewSlide
	Date dateCreated = new Date()
	Date lastUpdated = new Date()

	String toString() { title }

	String getPreview() {
		if (this.previewSlide) {
			return this.previewSlide.content
		} else if (slides.size() > 0) {
			return this.slides[0].content
		} else {
			return '<h2>N/A</h2>'
		}
	}

	static transients = ['preview']

	static hasMany = [ 
		slides : Slide,
		collaborators: User, 
		viewers: User]

	static belongsTo = [ author : User ]

	static constraints = {
		previewSlide nullable:true
	}

	static mapping = {
		sort dateCreated: 'desc'
	}
}
