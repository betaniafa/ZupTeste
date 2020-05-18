Feature: Get book by ISBN
#  Scenario: User calls web service to get a book by its ISBN
#    Given a book exists with an isbn of x
#    When a user retrieves the book by isbn
#    Then the status code is x

  Scenario: Create a new book
    Given a book exists with an isbn of x
    When I post
    Then the status code is x
