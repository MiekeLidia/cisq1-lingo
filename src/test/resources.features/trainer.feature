Feature: Training for Lingo
  As a Lingo participant
  I want to practice guessing 5, 6 and 7 letter words
  In order to prepare for my TV appearance



Scenario: A game is started
  When the first letter appears
  Then I can guess the word




Scenario Outline: Start a new round
  Given I am playing a game
  And the round was won
  And the last word had "<previous length>" letters
  When I start a new round
  Then the word to guess has "<next length>" letters

  Examples:
    | previous length | next length |
    | 5               | 6           |
    | 6               | 7           |
    | 7               | 5           |

  Given I am playing a game
  And the round was lost
  Then I cannot start a new round




Scenario Outline: Guessing a word
  Given I am playing a game
  And a "<word>" has been chosen
  When I try a "<guess>"
  Then I should see "<feedback>"

  Examples:
  | word | guess | feedback

  Given I am playing a game
  And I guesed a word correct
  Then My score my score up's by "score"

  Given I am playing a game
  And I guesed 5 times
  And I did not gues the right word
  Then I have lost this round
  And I can not try an other guess

  Given I am playing a game
  And the right word is guesed
  Then I cannot guess agian

  Given I am playing a game
  And I am guessing
  Then I cannot start a new round






Scenario: No round without a game
  When I have not started a game
  Then I can not start a new round