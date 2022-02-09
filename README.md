Grails Force SSL
================

The Grails Force SSL Plugin provides an annotation for controllers to force ssl url endpoints. For example, you may want to restrict a shopping cart page or login page to SSL.


Configuration
-------------
By default, the SSL plugin is enabled for all environments, with the exception of `Development`. This can be overridden by adjusting your `Config.groovy`

```groovy
grails.plugin.forceSSL.enabled = false
```

### Grails 3

```yaml
grails:
    plugin:
        forceSSL:
            enabled: true
```



It is also possible to override the https port for the redirect if you want to via:

```groovy
grails.plugin.forceSSL.sslPort = 6443 //optional
```

Usage
-----
Simply import the SSL annotation and apply at the controller level or at the annotation level.

```groovy
import com.bertramlabs.plugins.SSLRequired

@SSLRequired //Will encrypt entire controller
class SessionController {
  @SSLRequired //Or here for action level
  def signin() {
    //Signin Code Here
  }
}
```

Another option is to use a configuration mapping to identify which controllers you wish to be restricted to SSL:

```groovy
  grails {
    plugin {
      forceSSL {
        enabled = true
        dashboard {
          index = true
        }
        home = true
      }
    }       
  }
```