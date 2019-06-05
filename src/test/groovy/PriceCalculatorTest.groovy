import spock.lang.Specification

class PriceCalculatorTest extends Specification {
    void setup() {}

    def "should be non null"() {
        when:
        def testedObject = new PriceCalculator()
        then:
        testedObject != null
    }
}

class PriceCalculator {
    /*
Prices:
apple -> 20
beer -> 200
beer duo pack cost (15% discount)
if sum > 500 then 10% of additional discount
*/
}