package com.bertramlabs.plugins

import grails.core.GrailsApplication
import org.grails.web.util.GrailsApplicationAttributes
import groovy.util.logging.Slf4j

@Slf4j
class ForceSSLInterceptor {
	GrailsApplication grailsApplication

	int order = HIGHEST_PRECEDENCE

	ForceSSLInterceptor() {
		matchAll()
	}

	boolean before() { 
		Boolean enabled = grailsApplication.config.getProperty('grails.plugin.forceSSL.enabled',Boolean,false)
		Integer sslPort = grailsApplication.config.getProperty('grails.plugin.forceSSL.sslPort',Integer,null)
		Map interceptUrlMap = grailsApplication.config.getProperty('grails.plugin.forceSSL.interceptUrlMap',Map,null)
		if(!enabled) {
			return false
		}
		def controller = request.getAttribute(GrailsApplicationAttributes.GRAILS_CONTROLLER_CLASS)

		if(!controller) {
				return true
		}

		def method = controller.clazz.declaredMethods.find { it.name == actionName }

		def annotation = method?.getAnnotation(SSLRequired) ?: controller.clazz.getAnnotation(SSLRequired)
		
		if(!annotation) {
			if(!interceptUrlMap ||  interceptUrlMap[request.requestURI] != true) {
				return true
			}
		}
		response.setHeader('Strict-Transport-Security','max-age=31536000')
		if(!(request.isSecure() || request.getHeader('X-Forwarded-Proto')?.toLowerCase() == 'https')) {
			log.info("Forcing SSL Redirect To https://${request.serverName}${request.requestURI}")
			//Persist Flash Scope
			flash.keySet().each { key ->
					flash[key] = flash[key]
			}
			if(sslPort) {
				redirect url: "https://${request.serverName}:${sslPort}${request.requestURI}"
            } else {
                redirect url: "https://${request.serverName}${request.requestURI}"
            }
			
			return false
		}
		return true
	}

	boolean after() { true }

	void afterView() {
		// no-op
	}

}
