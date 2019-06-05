import spock.lang.Specification

class ModelTest extends Specification {

    PriceCalculatorModelV testedObject

    void setup() {
        testedObject = new PriceCalculatorModelV()
    }

    def "should return 0 when no items"() {
        when:
        def calculate = testedObject.calculate()
        then:
        calculate == 0

    }

    def "should return 20 when one apple added"() {

        expect:
        testedObject.addItems(addedItems)
        result == testedObject.calculate()
        where:
        addedItems                      | result
        List.of()                       | 0
        List.of("apple")                | 20
        List.of("apple", "apple")       | 40
        List.of("beer")                 | 200
        List.of("beer", "beer")         | 340
        List.of("beer", "beer", "beer") | 540 - 54
        List.of("beer", "beer", "beer", "beer", "apple") | 700 - 70

    }

    def "should return 40 when two apples added separately"() {
        testedObject.addItems(List.of("apple"))
        testedObject.addItems(List.of("apple"))
        when:
        def result = testedObject.calculate()
        then:
        result == 40
    }
}

class PriceCalculatorModelV {
    private List<String> items = List.of()
    private Map<String, Integer> prices = ["apple": 20, "beer": 200]
    /*
Prices:
apple -> 20
beer -> 200
beer duo pack cost (15% discount)
if sum > 500 then 10% of additional discount
*/

    int calculate() {
        def initialPrice = items.stream()
                .mapToInt(prices.&get)
                .sum()

        int discount = calculateDiscount()
        def priceAfterBeerDiscount = initialPrice - discount
        if (priceAfterBeerDiscount > 500) {
            return 0.9 * priceAfterBeerDiscount
        }
        return priceAfterBeerDiscount
    }

    private int calculateDiscount() {
        def decimal = items.stream().filter("beer".&equals).count().intdiv(2)
        decimal * prices["beer"] * 2 * 0.15
    }

    void addItems(List<String> items) {
        this.items += items
    }
}