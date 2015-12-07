package grails3x.postgresql.dialect

import grails.test.mixin.integration.Integration
import grails.transaction.Transactional
import spock.lang.Specification

@Integration
@Transactional
class SizeConstraintsSpec extends Specification {

    void "String property should requires size-ish constraints"() {
        given:
        def domainA = new DomainA(name: "Mike", value: "x" * 100, infinite: "y" * 256)

        when:
        domainA.save(flush: true)

        then:
        !domainA.hasErrors()
    }
}
