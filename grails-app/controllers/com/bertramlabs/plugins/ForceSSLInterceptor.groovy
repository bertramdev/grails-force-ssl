package com.bertramlabs.plugins

import grails.core.GrailsApplication
import org.grails.web.util.GrailsApplicationAttributes

class ForceSSLInterceptor {
	GrailsApplication grailsApplication

	int order = HIGHEST_PRECEDENCE

	ForceSSLInterceptor() {
		matchAll()
	}

	boolean before() { 
		if(grailsApplication.config.grails.plugin.forceSSL.enabled instanceof Closure) {
			if (!grailsApplication.config.grails.plugin.forceSSL.enabled(request)) {
					return true
			}
			} else if(grailsApplication.config.grails.plugin.forceSSL.enabled == false ) {
					return true
			}
			def controller = request.getAttribute(GrailsApplicationAttributes.GRAILS_CONTROLLER_CLASS)

			if(!controller) {
					return true
			}

			def method = controller.clazz.declaredMethods.find { it.name == actionName }

			def annotation = method?.getAnnotation(SSLRequired) ?: controller.clazz.getAnnotation(SSLRequired)
			
			if(!annotation) {
				if(!grailsApplication.config.grails.plugin.forceSSL.interceptUrlMap ||  grailsApplication.config.grails.plugin.forceSSL.interceptUrlMap[request.requestURI] != true) {
					return true
				}
			}

		if(!(request.isSecure() || request.getHeader('X-Forwarded-Proto')?.toLowerCase() == 'https')) {
			log.info("Forcing SSL Redirect To https://${request.serverName}${request.requestURI}")
			//Persist Flash Scope
			flash.keySet().each { key ->
					flash[key] = flash[key]
			}
			if(grailsApplication.config.grails.plugin.forceSSL.sslPort) {
				redirect url: "https://${request.serverName}:${grailsApplication.config.grails.plugin.forceSSL.sslPort}${request.requestURI}"
            } else {
                redirect url: "https://${request.serverName}${request.requestURI}"
            }
			
			return false
		}
	}

	boolean after() { true }

	void afterView() {
		// no-op
	}

}
