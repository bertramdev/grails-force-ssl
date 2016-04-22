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

The enabled flag can also be defined as a closure which will get passed the request attribute. This allows for evaluation on a per requeset level as to wether or not SSL should be enforced. Can be rather useful for disabling forced SSL for certain URL endpoints (for example server endpoints not behind a load balancer).

```groovy
grails.plugin.forceSSL.enabled = { request ->
  if(request.serverName == 'app1.bertramlabs.com') {
    return false
  }
  return true
}
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