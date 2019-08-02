package it.polito.dp2.RNS.sol1.readers;

// TODO abstract class? interface?
public class IdentifiedEntityReader implements it.polito.dp2.RNS.IdentifiedEntityReader {

    protected String id;

    IdentifiedEntityReader(String id) {
        if (id == null || id.isEmpty()) {
            System.err.println("Invalid ID.");
            System.exit(1);
        }

        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
