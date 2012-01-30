package com.github.aaronzirbes.impress.js

class Slide {

	String title = "New Slide"
	String content = """<p>Lorem ipsum dolor sit amet, consectetur 
adipiscing elit. Vestibulum placerat pretium dictum. Vivamus 
quis euismod urna. Sed ut nibh eget purus dignissim aliquet. 
Proin sodales tempus nulla vel faucibus. Pellentesque habitant morbi 
tristique senectus et netus et malesuada fames ac turpis egestas</p>"""
	Integer sortOrder = 1
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
		sortOrder unique: 'presentation'
    }

	static mapping = {
		content type:'text'
		sort "sortOrder"
	}
}
