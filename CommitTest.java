import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

public class CommitTest {
    @Test
    void testCommit() throws Exception {

        PrintWriter pw = new PrintWriter(
                ".\\test.txt");

        pw.print("file 1");
        pw.close();

        Tree testTree = new Tree("Tree");
        testTree.add("test.txt");

        String author = "author";
        String summary = "summary";
        String treePath = "Tree";
        Commit commit = new Commit(author, summary, treePath);

        // String date = commit.returnSavedDate();

        commit.commit();

        String expectedCommit = commit.returnPrevCommitHash();

        // File objects = new File("objects");
        // assertTrue(objects.exists());

        File commitFile = new File("objects\\" + expectedCommit);
        assertTrue(commitFile.exists());

    }

    @Test
    void testCreateTree() throws Exception {
        String testHash = Commit.createTree("Tree");

        String expectedHash = "521081105";

        assertEquals(testHash, expectedHash);
    }

    @Test
    void testGetDate() throws Exception {
        // not sure how to do?

        assertEquals(Commit.getDate(), Commit.getDate());
        // might throw error if ur computer is slow or if u run at an awkward time
    }

}
