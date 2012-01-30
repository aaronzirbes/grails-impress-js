package com.github.aaronzirbes.impress.js

class Slide {

	String title = "New Slide"
	String content = """<p>Lorem ipsum dolor sit amet</p>"""
	Integer sortOrder = 0
	Integer positionX = 0
	Integer positionY = 0
	Integer positionZ = 0
	Integer rotationX = 0
	Integer rotationY = 0
	Integer rotationZ = 0
	BigDecimal scale = 1

	String toString() { title }

	static belongsTo = [ presentation : Presentation ]

    static constraints = {
		sortOrder unique: 'presentation', range:1..254
    }

	static mapping = {
		content type:'text'
		sort "sortOrder"
	}
}
