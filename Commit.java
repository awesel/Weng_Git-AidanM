
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Commit {

    String author;
    String date;
    String summary;

    // not sure if necessary
    String prevCommit;

    String treeHash;

    // creates a Commit file
    public Commit(String author, String summary, String treePath) throws Exception {
        // create commit file
        // this.author = author;
        // this.summary = summary;

        File file = new File("Commit");
        file.createNewFile();

        String treeHash = Blob.sha1(Blob.read(treePath));

        // write to file
        StringBuilder sb = new StringBuilder("");

        // tree, prev, next, author, date, summary
        // -------------------------- surprise ternary :D
        sb.append(treeHash + "\n" + ((prevCommit == null) ? "" : prevCommit) + "\n\n" + author + "\n"
                + getDate()
                + "\n" + summary);

        FileWriter fw = new FileWriter("Commit");
        fw.write(sb.toString());

        fw.close();

    }

    // creates file and puts in objects folder & updates previous one
    public void commit() throws Exception {

        // create the file in objects folder
        Blob.blob("Commit");
        String commitHash = Blob.sha1("Commit");

        // check if has previous commit and add to it
        // read & replaces line 3 (with "next hash")
        File commitFile = new File("objects", commitHash);
        if (commitFile.exists()) {

            // read
            StringBuilder sb = new StringBuilder("");
            BufferedReader br = new BufferedReader(new FileReader(commitFile));
            int line = 1;

            while (br.ready()) {
                if (line != 3) {
                    sb.append(br.readLine());
                } else {
                    sb.append(prevCommit);
                    br.readLine();
                }
                line++;
            }

            br.close();

            // write
            FileWriter fw = new FileWriter(commitFile);
            fw.write(sb.toString());

            fw.close();

        }

        // set previous commit to current one
        prevCommit = commitHash;

    }

    public static String getDate() {
        // long milliTime = System.currentTimeMillis();

        LocalDateTime ldt = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");

        return (ldt.format(dtf));
    }

    // creates tree for use in constructor
    public static String createTree(String fileName) throws Exception {
        Tree tree = new Tree(fileName);
        return Integer.toString(tree.hashCode());
    }

    public static void main(String[] args) throws Exception {
        Tree testTree = new Tree("Tree");

        testTree.add("test.txt");

        Commit commitTest = new Commit("me", "ballsballsballs", "Tree");

        commitTest.commit();

        System.out.println("test done");

    }

}
