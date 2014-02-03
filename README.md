Grails Force SSL
================

The Grails Force SSL Plugin provides an annotation for controllers to force ssl url endpoints. For example, you may want to restrict a shopping cart page or login page to SSL.


Configuration
-------------
By default, the SSL plugin is enabled for all environments, with the exception of `Development`. This can be overridden by adjusting your `Config.groovy`

```groovy
grails.plugin.forceSSL.enabled = false
```

Usage
-----
Simply import the SSL annotation and apply at the controller level or at the annotation level.

```groovy
import com.bertramlabs.plugins.SSL

@SSL //Will encrypt entire controller
class SessionController {
  @SSL //Or here for action level
	def signin() {
    //Signing Code Here
  }
}
```
