package com.bertramlabs.plugins
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
class ForceSSLFilters {
    def grailsApplication
    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(grailsApplication.config.grails.plugin.forceSSL.enabled == false ) {
                    return true
                }
                def controller = request.getAttribute(GrailsApplicationAttributes.GRAILS_CONTROLLER_CLASS)

                if(!controller) {
                    return true
                }

                def method = controller.clazz.declaredMethods.find { it.name == actionName }

                def annotation = method?.getAnnotation(SSLRequired) ?: controller.clazz.getAnnotation(SSLRequired)

                if(!annotation) {
                    return true
                }

                if(!(request.isSecure() || request.getHeader('X-Forwarded-Proto')?.toLowerCase() == 'https')) {
                    log.info("Forcing SSL Redirect To https://${request.serverName}${request.forwardURI}")
                    redirect url: "https://${request.serverName}${request.forwardURI}"
                    return false
                }
            }
        }
    }
}
