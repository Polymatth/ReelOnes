package use_case.goprofile;
/**
 * The Input Data for the Go Profile Use Case.
 */

public class GoProfileInputData {

    private final String username;

    public GoProfileInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

}
