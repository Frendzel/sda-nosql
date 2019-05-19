package com.sda


import spock.lang.Specification

//TODO add more tests for other methods
class PropertyLoaderTest extends Specification {

    def "Should return correct #value for #key"() {
        given:
            PropertyLoader loader = new PropertyLoader()
            loader.init()
        expect:
            value == loader.getProperty(key)
        where:
            key        || value
            "db"       || "test"
            "user"     || "test"
            "password" || "test123"
            "address"  || "127.0.0.1"
    }
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
