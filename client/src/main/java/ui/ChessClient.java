package ui;

import exception.ResponseException;
import server.ResultInfo;


public class ChessClient {
    private String visitorName = null;
    private final ServerFacade server;
    private final String serverUrl;
    private StateOfSystem state;

    public ChessClient(String serverUrl) {
        this.server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
        this.state = StateOfSystem.SIGNEDOUT;
    }

    public void setState(StateOfSystem state) {
        this.state = state;
    }

    public StateOfSystem getState() {
        return this.state;
    }


    public String register(String username, String password, String email) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.registerUser(username,password,email);
            if (result.getStatus() == 200) {
                setState(StateOfSystem.SIGNEDIN);
                return "logged in as " + username;
            } else {
                return ("Error: " + result.getStatus());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), "Error: bad request");
        }
    }
    public String signIn(String username, String password) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.loginUser(username, password);
            if (result.getStatus() == 200) {
                setState(StateOfSystem.SIGNEDIN);
                return "logged in as " + username;
            } else {
                return ("Error: " + result.getStatus());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), "Error: bad request");
        }
    }

//
//    public String listPets() throws ResponseException {
//        assertSignedIn();
//        var pets = server.listPets();
//        var result = new StringBuilder();
//        var gson = new Gson();
//        for (var pet : pets) {
//            result.append(gson.toJson(pet)).append('\n');
//        }
//        return result.toString();
//    }
//
//    public String adoptPet(String... params) throws ResponseException {
//        assertSignedIn();
//        if (params.length == 1) {
//            try {
//                var id = Integer.parseInt(params[0]);
//                var pet = getPet(id);
//                if (pet != null) {
//                    server.deletePet(id);
//                    return String.format("%s says %s", pet.name(), pet.sound());
//                }
//            } catch (NumberFormatException ignored) {
//            }
//        }
//        throw new ResponseException(400, "Expected: <pet id>");
//    }
//
//    public String adoptAllPets() throws ResponseException {
//        assertSignedIn();
//        var buffer = new StringBuilder();
//        for (var pet : server.listPets()) {
//            buffer.append(String.format("%s says %s%n", pet.name(), pet.sound()));
//        }
//
//        server.deleteAllPets();
//        return buffer.toString();
//    }
//
//    public String signOut() throws ResponseException {
//        assertSignedIn();
//        ws.leavePetShop(visitorName);
//        ws = null;
//        state = State.SIGNEDOUT;
//        return String.format("%s left the shop", visitorName);
//    }
//
//    private Pet getPet(int id) throws ResponseException {
//        for (var pet : server.listPets()) {
//            if (pet.id() == id) {
//                return pet;
//            }
//        }
//        return null;
//    }
//
//    public String help() {
//        if (state == State.SIGNEDOUT) {
//            return """
//                    - signIn <yourname>
//                    - quit
//                    """;
//        }
//        return """
//                - list
//                - adopt <pet id>
//                - rescue <name> <CAT|DOG|FROG|FISH>
//                - adoptAll
//                - signOut
//                - quit
//                """;
//    }
//
//    private void assertSignedIn() throws ResponseException {
//        if (state == State.SIGNEDOUT) {
//            throw new ResponseException(400, "You must sign in");
//        }
//    }
}