import com.github.aaronzirbes.idm.User
import com.github.aaronzirbes.idm.Role
import com.github.aaronzirbes.idm.UserRole
import com.github.aaronzirbes.impress.js.Presentation
import com.github.aaronzirbes.impress.js.Slide

class BootStrap {

	def init = { servletContext ->
		environments {
			development {
				def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
				def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

				def ajz = new User(username: 'ajz@umn.edu', enabled: true, password: 'password')
				ajz.save(flush: true)

				UserRole.create ajz, adminRole, true

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
