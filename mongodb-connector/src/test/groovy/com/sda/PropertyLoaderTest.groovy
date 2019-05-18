package com.sda

import spock.lang.PendingFeature
import spock.lang.Specification

class PropertyLoaderTest extends Specification {
    @PendingFeature
    def "Should return correct #value for #key"() {
        given:
            PropertyLoader loader = new PropertyLoader()
            loader.init()
        expect:
            value == loader.properties.get(key)
        where:
            key        || value
            "DB"       || "test"
            "user"     || "test"
            "password" || "test123"
            "address"  || "127.0.0.1"
    }
    @PendingFeature
    def "Should return correct value for getUser method"(){
        given:
            PropertyLoader loader = new PropertyLoader()
            loader.init()
        when:
            def user = loader.getUser()
        then:
            user == "test"
    }
}
