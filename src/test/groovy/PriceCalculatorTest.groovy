import spock.lang.Specification

/*
Implement class which will calculate prices.
Prices:
apple -> 20
beer -> 200
beer duo pack cost (15% discount)
if sum > 500 then 10% of additional discount
*/

class PriceCalculatorTest extends Specification {
    private PriceCalculator testedObject

    void setup() {
        testedObject = new PriceCalculator()
    }

    def "should return 0 when 0 items added"() {
        when:
        def result = testedObject.calculate()

        then:
        result == 0
    }

    def "should return correct value for params"(){
        expect:
        testedObject.add(listOfItems)
        result == testedObject.calculate()

        where:
        listOfItems | result
        List.of()   | 0
        List.of("apple")   | 20
        List.of("apple", "apple")   | 40
    }
}

class PriceCalculator {

    private List<String> items = List.of()

    void add(List<String> items) {
        this.items = items
    }

    int calculate() {
        return items.size() * 20
    }
}