package grails3x.postgresql.dialect

import grails.test.mixin.integration.Integration
import grails.transaction.Transactional
import spock.lang.Specification

@Integration
@Transactional
class SequenceGeneratorSpec extends Specification {

    void "id should be independently generated"() {
        when:
        3.times { new DomainA(name: "Mike", value: "x", infinite: "y", max255: "z").save(failOnError: true, flush: true) }
        3.times { new DomainB(name: "David").save(failOnError: true, flush: true) }
        3.times { new DomainC(name: "Junko").save(failOnError: true, flush: true) }

        then:
        DomainA.list()*.id == [1, 2, 3]
        DomainB.list()*.id == [1, 2, 3]
        DomainC.list()*.id == [1, 2, 3]
    }
}
