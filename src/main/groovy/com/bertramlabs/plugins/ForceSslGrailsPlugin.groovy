package com.bertramlabs.plugins

import grails.util.Environment
import grails.plugins.*

class ForceSslGrailsPlugin extends Plugin {
    def grailsVersion   = "3.1.0 > *"

    def title = "Force SSL Plugin" // Headline display name of the plugin
    def author = "David Estes"
    def authorEmail = "destes@bcap.com"
    def description = 'Creates a simple annotation to mark controller/actions as SSL restricted and performs the appropriate redirect.'
    def documentation   = "https://github.com/bertramdev/grails-force-ssl"
    def license = "APACHE"
    def organization = [name: "Bertram Labs", url: "http://www.bertramlabs.com/"]
	def developers      = [
		[name: 'David Estes', email: 'destes@bcap.com'],
		[name: 'Jeremy Leng',email: 'jleng@bcap.com']]

    def issueManagement = [system: "GITHUB", url: "https://github.com/bertramdev/grails-force-ssl/issues"]
    def scm = [url: "https://github.com/bertramdev/grails-force-ssl"]
    def doWithSpring = {
        if(!grailsApplication.config.grails.plugin.forceSSL.containsKey('enabled')) {
            grailsApplication.config.grails.plugin.forceSSL.enabled = !Environment.isDevelopmentMode()
        }
    }
}
