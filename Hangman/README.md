Hello ,
I implemented all operations of the hangman API. Below is a list of the URLs of operations 
I also included a fat jar named Hangman-0.0.1-SNAPSHOT-jar-with-dependencies.jar  where you can click and start grizzly http server and test urls.

I added comments in the code also.


•	Get single Player with ID http://localhost:8080/hangman/baseController/player/1
•	Get All Players http://localhost:8080/hangman/baseController/player
•	Get All Games http://localhost:8080/hangman/baseController/game
•	Get Single Game with IDhttp://localhost:8080/hangman/baseController/game/1
•	Create Game with given player http://localhost:8080/hangman/baseController/createGame/BasePlayer/25/1
•	Make a guess in the game with game id and a single letter (PUT request) http://localhost:8080/hangman/baseController/2/b
•	Delete a single game with given ID http://localhost:8080/hangman/baseController/2
