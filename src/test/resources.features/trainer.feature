Feature: Training for Lingo
  As a Lingo participant
  I want to practice guessing 5, 6 and 7 letter words
  In order to prepare for my TV appearance

Scenario Outline: A game is started
  When the first letter appears
  Then I can guess the word



Scenario Outline: Guessing a word
  Givven I am playing a game
  And a "<word>" has been chosen
  When I try a "<guess>"
  Then I should see "<feedback>"

  Examples:
  | word | guess | feedback


