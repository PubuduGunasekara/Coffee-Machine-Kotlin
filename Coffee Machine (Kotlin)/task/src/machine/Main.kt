class CoffeeMachine(
    var water: Int = 400,
    var milk: Int = 540,
    var coffeeBeans: Int = 120,
    var disposableCups: Int = 9,
    var money: Int = 550,
    var state: String = "choosing an action"
) {

    fun processInput(input: String) {
        when (state) {
            "choosing an action" -> chooseAction(input)
            "choosing a coffee type" -> chooseCoffeeType(input)
            "filling water" -> fillWater(input)
            "filling milk" -> fillMilk(input)
            "filling coffee beans" -> fillCoffeeBeans(input)
            "filling cups" -> fillCups(input)
        }
    }

    private fun chooseAction(input: String) {
        when (input) {
            "buy" -> {
                state = "choosing a coffee type"
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            }
            "fill" -> {
                state = "filling water"
                println("Write how many ml of water you want to add:")
            }
            "take" -> takeMoney()
            "remaining" -> displayStatus()
            "exit" -> return
        }
    }

    private fun chooseCoffeeType(input: String) {
        when (input) {
            "1" -> makeCoffee(250, 0, 16, 4)
            "2" -> makeCoffee(350, 75, 20, 7)
            "3" -> makeCoffee(200, 100, 12, 6)
            "back" -> state = "choosing an action"
        }
    }

    private fun makeCoffee(neededWater: Int, neededMilk: Int, neededCoffeeBeans: Int, cost: Int) {
        if (water >= neededWater && milk >= neededMilk && coffeeBeans >= neededCoffeeBeans && disposableCups >= 1) {
            println("I have enough resources, making you a coffee!")
            water -= neededWater
            milk -= neededMilk
            coffeeBeans -= neededCoffeeBeans
            disposableCups -= 1
            money += cost
        } else {
            println("Sorry, not enough resources!")
        }
        state = "choosing an action"
    }

    private fun fillWater(input: String) {
        water += input.toInt()
        state = "filling milk"
        println("Write how many ml of milk you want to add:")
    }

    private fun fillMilk(input: String) {
        milk += input.toInt()
        state = "filling coffee beans"
        println("Write how many grams of coffee beans you want to add:")
    }

    private fun fillCoffeeBeans(input: String) {
        coffeeBeans += input.toInt()
        state = "filling cups"
        println("Write how many disposable cups you want to add:")
    }

    private fun fillCups(input: String) {
        disposableCups += input.toInt()
        state = "choosing an action"
    }

    private fun takeMoney() {
        println("I gave you \$$money")
        money = 0
    }

    private fun displayStatus() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffeeBeans g of coffee beans")
        println("$disposableCups disposable cups")
        println("$$money of money")
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()

    while (true) {
        print("Write action (buy, fill, take, remaining, exit): ")
        val input = readLine() ?: ""
        coffeeMachine.processInput(input.trim().toLowerCase())
        if (input == "exit") break
    }
}
