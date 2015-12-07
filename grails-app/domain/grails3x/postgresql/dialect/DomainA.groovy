package grails3x.postgresql.dialect

class DomainA {

    String name
    String value
    String infinite
    String max255

    static constraints = {
        name  size: 4..10       //=> name: VARCHAR(10)
        value maxSize: 100      //=> value: VARCHAR(100)
        max255 maxSize: 255     // DialectによってDB上ではTEXT型になってしまうが、バリデーションのためにmaxSizeも指定する必要がある
    }

    static mapping = {
        //infinite type: 'text'   //=> infinite: TEXT
        max255 sqlType: "varchar(255)" // Dialectでの解釈を逆に強制上書きする

        //id generator: 'sequence', params: [sequence:'domain_a_id_seq']
    }
}
