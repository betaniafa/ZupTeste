Feature: New book

  Scenario: Create a new book
    Given an insertion of a book with id '01', title 'O Poder do Hábito', description 'O autor toca no outro assunto importantíssimo, que é a criação de hábitos corretos.', pageCount '408', excerpt 'Os hábitos surgem sem a nossa permissão.', PublishDate '2012-01-10T20:43:36.324Z'
    When I post new book
    Then the book with title 'O Poder do Hábito' was successfully created and statusCode return '200'

  Scenario: Search a book
    Given fetch active book
    When fetch book with id '190'
    Then the book with title 'Book 190' was successfully found