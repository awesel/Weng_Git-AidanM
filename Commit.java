
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Commit {

    String author;
    String date;
    String summary;

    String treeHash;

    // no previous
    public Commit(String author, String summary) {
        // create commit file

    }

    // yes previous
    public Commit(String author, String summary, String previous) {
        // create commit file

    }

    // creates file and puts in objects folder
    public void commit(String treePath) throws NoSuchAlgorithmException, IOException {

        // hash tree
        treeHash = Blob.sha1(Blob.read(treePath));

        // blobify commit file
        // read & skips line 3

    }

    public String getDate() {
        // long milliTime = System.currentTimeMillis();

        LocalDateTime ldt = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");

        return (ldt.format(dtf));
    }

}
