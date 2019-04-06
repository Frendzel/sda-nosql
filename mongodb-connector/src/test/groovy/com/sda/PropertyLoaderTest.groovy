package com.sda

import spock.lang.Specification

//TODO fill the test
class PropertyLoaderTest extends Specification {
    def "test getUser"() {
        given:
        PropertyLoader loader = new PropertyLoader()
        loader.init()
        expect:
        value == loader.properties.get(key)
        where:
        key  || value
        "DB" || "test"
    }
}
