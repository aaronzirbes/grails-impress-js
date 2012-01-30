package com.github.aaronzirbes.impress.js

import org.springframework.dao.DataIntegrityViolationException

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
class SlideController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [slideInstanceList: Slide.list(params), slideInstanceTotal: Slide.count()]
    }

    def create() {
        def slideInstance = new Slide(params)
		if (slideInstance.presentation && slideInstance.sortOrder == 0) {
			def presentation = slideInstance.presentation
			if (! presentation.slides) {
				slideInstance.sortOrder = 1
			} else {
				def random = new Random()
				// increment max sort order
				def lastSlide = presentation.slides.sort{it.sortOrder * -1}.getAt(0)
				slideInstance.sortOrder = lastSlide.sortOrder + 1

				// Pull last position
				slideInstance.positionX = lastSlide.positionX
				slideInstance.positionY = lastSlide.positionY
				slideInstance.positionZ = lastSlide.positionZ

				// pull last rotation settings
				slideInstance.rotationX = lastSlide.rotationX
				slideInstance.rotationY = lastSlide.rotationY
				slideInstance.rotationZ = lastSlide.rotationZ

				// Jiggle it a little bit
				def rotation = random.nextInt(20) - 10
				slideInstance.rotationZ = slideInstance.rotationZ + rotation

				// Randomly Move around
				def done = false
				while ( ! done) {
					def direction = random.nextInt(4)
					if (direction == 0) { slideInstance.positionX = slideInstance.positionX + 1000 }
					else if (direction == 1) { slideInstance.positionY = slideInstance.positionY + 1000 }
					else if (direction == 2) { slideInstance.positionY = slideInstance.positionY - 1000 }
					else if (direction == 3) { slideInstance.positionX = slideInstance.positionX - 1000 }

					def existingSlide = Slide.findWhere(positionX: slideInstance.positionX, positionZ: slideInstance.positionY)
					if (! existingSlide) { done = true }
				}
			}
		}

		if (slideInstance.save(flush:true)) {
			slideInstance.refresh()
			redirect(action:'edit', id: slideInstance.id)
			return
		} else {
			flash.message = message(code: 'default.created.message', args: [message(code: 'slide.label', default: 'Slide'), slideInstance.id])
            redirect(controller:presentation, action: "list")
            return
		}
    }

    def show() {
        def slideInstance = Slide.get(params.id)
        if (!slideInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(action: "list")
            return
        }

        [slideInstance: slideInstance]
    }

    def edit() {
        def slideInstance = Slide.read(params.id)
        if (!slideInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(action: "list")
            return
        }
		def prevSlideInstance = Slide.findBySortOrder(slideInstance.sortOrder - 1)
		def nextSlideInstance = Slide.findBySortOrder(slideInstance.sortOrder + 1)

        [slideInstance: slideInstance, prevSlideInstance: prevSlideInstance, nextSlideInstance: nextSlideInstance]
    }

    def update() {
        def slideInstance = Slide.get(params.id)
        if (!slideInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (slideInstance.version > version) {
                slideInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'slide.label', default: 'Slide')] as Object[],
                          "Another user has updated this Slide while you were editing")
                render(view: "edit", model: [slideInstance: slideInstance])
                return
            }
        }

        slideInstance.properties = params

        if (!slideInstance.save(flush: true)) {
            render(view: "edit", model: [slideInstance: slideInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'slide.label', default: 'Slide'), slideInstance.id])
        redirect(action: "edit", id: slideInstance.id)
    }

    def delete() {
        def slideInstance = Slide.get(params.id)
        if (!slideInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(action: "list")
            return
        }
		def presentationInstance = slideInstance.presentation
        try {
            slideInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(controller: "presentation", action: "edit", id: presentationInstance?.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'slide.label', default: 'Slide'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
