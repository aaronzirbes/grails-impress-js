package com.github.aaronzirbes.impress.js

import org.springframework.dao.DataIntegrityViolationException
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
class PresentationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [presentationInstanceList: Presentation.list(params), presentationInstanceTotal: Presentation.count()]
    }

    def create() {
        [presentationInstance: new Presentation(params)]
    }

    def save() {
        def presentationInstance = new Presentation(params)
		presentationInstance.author = User.findByUsername('ajz@umn.edu')
        if (!presentationInstance.save(flush: true)) {
            render(view: "create", model: [presentationInstance: presentationInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'presentation.label', default: 'Presentation'), presentationInstance.id])
        redirect(action: "edit", id: presentationInstance.id)
    }

    def show() {
        def presentationInstance = Presentation.get(params.id)
        if (!presentationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "list")
            return
        }

        [presentationInstance: presentationInstance]
    }

    def edit() {
        def presentationInstance = Presentation.get(params.id)
        if (!presentationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "list")
            return
        }

        [presentationInstance: presentationInstance]
    }

    def update() {
        def presentationInstance = Presentation.get(params.id)
        if (!presentationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (presentationInstance.version > version) {
                presentationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'presentation.label', default: 'Presentation')] as Object[],
                          "Another user has updated this Presentation while you were editing")
                render(view: "edit", model: [presentationInstance: presentationInstance])
                return
            }
        }

        presentationInstance.properties = params

        if (!presentationInstance.save(flush: true)) {
            render(view: "edit", model: [presentationInstance: presentationInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'presentation.label', default: 'Presentation'), presentationInstance.id])
        redirect(action: "edit", id: presentationInstance.id)
    }

    def delete() {
        def presentationInstance = Presentation.get(params.id)
        if (!presentationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "list")
            return
        }

        try {
            presentationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'presentation.label', default: 'Presentation'), params.id])
            redirect(action: "edit", id: params.id)
        }
    }
}
