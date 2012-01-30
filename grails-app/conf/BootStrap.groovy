import com.github.aaronzirbes.idm.User
import com.github.aaronzirbes.idm.Role
import com.github.aaronzirbes.idm.UserRole
import com.github.aaronzirbes.impress.js.Presentation
import com.github.aaronzirbes.impress.js.Slide

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
class BootStrap {

	def init = { servletContext ->

		def adminRole = Role.findOrSaveByAuthority('ROLE_ADMIN')
		def userRole = Role.findOrSaveByAuthority('ROLE_USER')
		def ajz = User.findByUsername('ajz@umn.edu')
		if (! ajz) {
			ajz = new User(username: 'ajz@umn.edu', enabled: true, password: 'password').save(flush:true)
			UserRole.create ajz, adminRole, true
		}

		environments {
			development {

				assert User.count() == 1
				assert Role.count() == 2
				assert UserRole.count() == 1

				def slides = [
					[ title: 'Intro', content: '<q>Replacing Acegi Security with Spring Security in a Grails 1.3.x application.</q>' ],
					[ title: 'Task', content: "<q>So, it's time to upgrade your old <strong>Acegi</strong> based Grails Applications to the power of <strong>Spring Security</strong>.</q>" ],
					[ title: 'What to Do', content: "<q>What are the <strong>steps</strong> you need to take to migrate <strong>your</strong> application?</q>" ],
					[ title: 'Outline', content: """<ul>
					               <li>Pre-Clean the Code Base</li>
									   <li>Test/Run the App</li>
									   <li>Swap Acegi for Spring Security plugin(s)</li>
									   <li>Update annotation</li>
									   <li>Update security service calls</li>
									   <li>Update GSP tags</li>
									   <li>Test/Run the app</li>
									   <li>Prep the application server</li>
									   <li>Deploy and check the app server logs</li>
								   </ul>"""	],
					[ title: 'The End', content: '<h1>The End</h2>' ],
				]

				// Create Presentation
				def pres = new Presentation( author: ajz, 
					showHint: true,
					title: 'Migration from Acegi to Spring Security using LDAP, Shibboleth and Mock for Development',
					description: 'A guide to converting a Grails application using a custom Acegi authentication plugin to using the Spring Security open set of grails plugins.').save(flush:true)

				def location = -1000
				def order = 1
				slides.each{
					pres.addToSlides(sortOrder: order, positionX: location, title:it.title, content:it.content).save(flush:true)
					order++
					location += 1000
				}
			}
		}
    }
    def destroy = {
    }
}
