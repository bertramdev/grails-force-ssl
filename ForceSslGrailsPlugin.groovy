class ForceSslGrailsPlugin {
    // the plugin version
    def version = "0.1.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion   = "2.0 > *"

    def title = "Force SSL Plugin" // Headline display name of the plugin
    def author = "David Estes"
    def authorEmail = "destes@bcap.com"
    def description = 'Defines a standard corss-plugin security bridge implementation for better decoupling of authentication in plugin heavy applications.'

    def documentation   = "https://github.com/bertramdev/grails-force-ssl"
    def license = "APACHE"
    def organization = [name: "Bertram Labs", url: "http://www.bertramlabs.com/"]
    def issueManagement = [system: "GITHUB", url: "https://github.com/bertramdev/grails-force-ssl/issues"]
    def scm = [url: "https://github.com/bertramdev/grails-force-ssl"]
    def doWithSpring = {
        if(!application.config.grails.plugin.forceSSL.containsKey('enabled')) {
            application.config.grails.plugin.forceSSL.enabled = !Environment.isDevelopmentMode()
        }
    }
}
