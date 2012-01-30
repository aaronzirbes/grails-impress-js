package com.github.aaronzirbes.impress.js

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
