# Palindrome game
A REST application implementing a child's game "Palindrome".\
The technology used: Java + Spring Boot + Maven + JUnit.\
\
The Game Description:\
* The number of players is unlimited.\
* A player enters 

\
The programme can be tested with help of the unit tests from PalindromeServiceTest and UserServiceTest classes, as well as via Postman with the following commands:\
POST-method: http://localhost:8080/palindromme/play/{username}/{string}
            \A. returns the message "Not a palindrome. Try again" if the entered word is not a palindrome;\
             B. return the message "Already in use. Try again" if the entered word is a palindrome but already used by another player;\
             C. return the congratulation message as well as the number of earned points for this round and total score if the entered word is a palindrome.\
GET-method: http://localhost:8080/palindromme/scores
           \returns the total score table of all players sorted by the number of points(descending).
