package grails3x.postgresql.dialect

import grails.test.mixin.integration.Integration
import grails.transaction.Transactional
import spock.lang.Specification

@Integration
@Transactional
class SizeConstraintsSpec extends Specification {

    void "String property should requires size-ish constraints"() {
        given:
        def domainA = new DomainA(name: "Mike", value: "x" * 100, infinite: "y" * 256, max255: "z" * 255)

        when:
        domainA.save(flush: true)

        then:
        !domainA.hasErrors()
    }

    void "独自Dialectでのデフォルト型解釈をmappingで更に上書き指定する"() {
        given:
        def domainA = new DomainA(name: "Mike", value: "x" * 100, infinite: "y" * 256, max255: max255)

        when:
        domainA.save(flush: true)

        then:
        println domainA.errors
        domainA.hasErrors() == invalid

        where:
        max255    | invalid
        "z" * 1   | false
        "z" * 255 | false
        "z" * 256 | true
    }
}
