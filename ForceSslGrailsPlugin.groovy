import grails.util.Environment

class ForceSslGrailsPlugin {
    // the plugin version
    def version = "1.0.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion   = "2.0 > *"

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
        if(!application.config.grails.plugin.forceSSL.containsKey('enabled')) {
            application.config.grails.plugin.forceSSL.enabled = !Environment.isDevelopmentMode()
        }
    }
}
