package com.github.aaronzirbes.impress.js

import com.github.aaronzirbes.idm.User

/*
 * Copyright 2012 Aaron J. Zirbes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
			def firstSlide = this.slides.sort{ it.sortOrder }.getAt(0)
			return firstSlide?.content
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
