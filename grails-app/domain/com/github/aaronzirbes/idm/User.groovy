package com.github.aaronzirbes.idm

class User {

	transient springSecurityService

	String username
	String password
	String fullName
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String toString() { username }

	String getEmail() { username }

	static constraints = {
		username blank: false, unique: true, email: true
		password blank: false
		fullName nullable:true
	}

	static transients = [ 'email' ]

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
