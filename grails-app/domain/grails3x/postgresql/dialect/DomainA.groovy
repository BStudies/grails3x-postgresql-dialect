package grails3x.postgresql.dialect

class DomainA {

    String name
    String value
    String infinite

    static constraints = {
        name  size: 4..10       //=> name: VARCHAR(10)
        value maxSize: 100      //=> value: VARCHAR(100)
    }

    static mapping = {
        id generator: 'sequence', params: [sequence:'domain_a_id_seq']
    }
}
