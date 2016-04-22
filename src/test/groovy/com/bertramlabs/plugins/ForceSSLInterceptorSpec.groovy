package com.bertramlabs.plugins


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ForceSSLInterceptor)
class ForceSSLInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test forceSSL interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"home")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
