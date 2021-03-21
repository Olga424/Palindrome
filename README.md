# Palindrome game
A REST application implementing a child's game "Palindrome".\
The technology used: Java + Stream API + Spring Boot + Maven + JUnit.\
\
The Game Description:
* The number of players is unlimited.
* Each player enters a word which he/she considers a palindrome.
* A word can be used only one time per a round.
* The player gained the biggest number of points is the winner.

\
The programme can be tested with help of the unit tests from PalindromeServiceTest and UserServiceTest classes, as well as via Postman with the following commands:
* POST-method: http://localhost:8080/palindromme/play/{username}/{string}
\
             A. returns the message "Not a palindrome. Try again" if the entered word is not a palindrome;\
             B. returns the message "Already in use. Try again" if the entered word is a palindrome but already used by another player;\
             C. returns the congratulation message as well as the number of earned points for this round and total score if the entered word is a palindrome.\
* GET-method: http://localhost:8080/palindromme/scores
\
            returns the total score table of all players sorted by the number of points (descending).
