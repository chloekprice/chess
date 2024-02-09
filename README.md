# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## Chess Server Design
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWOZVYSgM536HHCkARYGGABBECE5cAJsOAAjYBxQxp8zJgDmUCAFdsMAMRpgVAJ4wASik1IOYKMKQQ0RgO4ALJGBSZEqUgBaAD4WakoALhgAbQAFAHkyABUAXRgAel1lKAAdNABvLMoTAFsUABoYXA4ON2hpSpQS4CQEAF9MCnDYELC2SQ4oqBs7HygACX5pBEoACiKoUoqqpVr6xubWgEpO1nYOGF6hEXEBwZhNFDAAVWz57KWd47EJKUPQtSiyAFEAGW+4EkYAsljAAGY6ErA7KYZ6nN6BXpdfqcKJoXQIBC7aj7d4wOGvGpREDDYQoW5zEHAMqVaprKDSJ5yeE1PGfGAASQAcj9LECqTSVjU6gyNi0EJyuUl4tDaMjcUdmYTziSUGTRLowB57sVqSgmSdlWz5FFubz+Q89ZVgJqPEkIABrdCS6UwG1a2FKs6HJF7AZRd12x3obEog6I0LIyJu232p1oUOUH2hfzoMBRABMAAYs3l8oG4+gOuhpBptHoDIZoPxLjBfhBbK5DJ5vL5U0FI6xo3FEqkMsoas40HmBcs6SLpB0oz1gvL-TAEA2kGhJmhppTLYLx-UdnPOHiCWcIpcbndRwaXt7ep8fv9AbLFnrwZCH57DVeQnvzgtnqGFaFDykYlSR8DUtR1R8ygvFkOGvE1JXNB8lmtWNg1cblXUDN9LwRX0cXnAs0L-AZkz6aNCPjRMel6dt0xgbNcwKCii0wEsyx0fQjG0FBnXrTQ9GYZsvB8PxkDTPFpyiaIBD+b4km+NJ0gHDghzyZiE2nd4vyiRd+M1Vd1ygWZ1N3P190Vd8gJPMDtRM7CYONCJbwBIF1OfCAoSwwDWQjPp9gDW1fy-A8vSA1QUBmHxjNQ+NoOVEJ2Rk-55KQq0Yy1QtXAAMUseIAFl0o8eyjV87SYGI8y8JoKAoio0jaMzHNWLXdiKyMYZpDrUYYAAcT1A4hNbUSAmYKrux6uTFM0PU1JikNNKq-yF1GPqyg4AyZiMuzgosnCiQuK4bOijK0Liq8Png5z7zciEPMK4qP07fDUUKoKzPDADQv25B7FWzhZjOhELq+WT72mspnhgHL8ouPUHtwp6wyicGUDe57wzGmrYYhuQ6t8hr6JzPMUeeYsWq0DjK2wXQoGwGZ4BAlQ-vcYS2zEjsyKxntkimmaTDm4cChRrk9SnLsZz8+dVTJP6NrmdTKmFvVTPRkLLP26zbWOoNYvhnzgfIUHXIF9zPNtPWMcRpbAzRsM1b2lVGb+2Ylagi3HIQ74+Wx1G5BdGUUfd0r3uRvUOQECrLc50OynDvGaPZuiGOJsOBDJ0sKbawwzAixc3BgAApCBl16p9DHkBBQAdYbxMxqTYmuPt0hR2aTvjPNaLgCBFygRXU7F7oJJDmAACti7QWWpk27XMsqTvu+gPvY4EFW7d2mDkcOrW7O8j7VEuo3Csy037t30iytHSP7Y3g6wGdlHw8B-X95Bu8gQfgQT8Ds-g-RmPfZEFfEIu8oj6FkD4e+epnhz0Tl3HulRzxBwNtcWIAhRApRJn7Eu38vpR20kAuu8cUyJ0almZqGdyycUMNoYAFhEBqlgMAbAdNCBOBcCzIatEh7dCkklOSCkMhqDxlbKWMw8ByyMqvf8+JcHEjEZIpBL98SyRSsATEJ8sLlR-gbPhqj1G3ShDg9We9EoqO+G6fRL4FhB0WvOAhiNoxEPgCQwmZC2JAA

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared tests`     | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
