# Server-Client Game

- The server starts running and listens for incoming connections on port 8080.
- Clients can connect to the server.
- When a client connects, a Client object is created to handle their communication.
- The Client object prompts the client to enter their username and adds the client to the server's client list.
- The Client object sends a welcome message to the client, providing instructions about the game.
- The Client object creates an instance of the GameLogic class to start the game logic.
- The GameLogic class generates a list of words for the game.
- The GameLogic class selects a random word from the list and sends a prompt to the client, asking them to type the word in reverse.
- The client enters their response.
- The GameLogic class compares the client's response with the reverse of the selected word.
- If the client's response matches the reverse of the word, the game declares the client as the winner and displays a success message.
- If the client's response does not match the reverse of the word, the game declares the client as the loser and displays a failure message.
- The game ends for that client.
- The server continues to accept connections from other clients, and the process repeats for each connected client.
- That's the basic flow of the game. The server handles multiple clients simultaneously, and each client plays an independent game with the server's game logic.

Enjoy ;)




