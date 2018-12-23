Feature: Calculator
    Scenario: Sum two positive numbers
        Given I have two numbers: 1 and 2
        When the calculator sums them
        Then I receive 3 as a result

    Scenario: Sum two negative numbers
        Given I have two numbers: -1 and -2
        When the calculator sums them
        Then I receive -3 as a result

    Scenario: Sum a negative and a positive number
        Given I have two numbers: 1 and -2
        When the calculator sums them
        Then I receive -1 as a result
