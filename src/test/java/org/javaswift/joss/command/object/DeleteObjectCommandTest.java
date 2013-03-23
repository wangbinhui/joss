package org.javaswift.joss.command.object;

import org.javaswift.joss.command.core.BaseCommandTest;
import org.javaswift.joss.exception.CommandException;
import org.javaswift.joss.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class DeleteObjectCommandTest extends BaseCommandTest {

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void deleteContainerSuccess() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(204);
        new DeleteObjectCommand(this.account, httpClient, defaultAccess, getObject("objectName")).call();
    }

    @Test (expected = NotFoundException.class)
    public void deleteContainerDoesNotExist() throws IOException {
        checkForError(404, new DeleteObjectCommand(this.account, httpClient, defaultAccess, getObject("objectName")));
    }

    @Test (expected = CommandException.class)
    public void unknownError() throws IOException {
        checkForError(500, new DeleteObjectCommand(this.account, httpClient, defaultAccess, getObject("objectName")));
    }

    @Test
    public void isSecure() throws IOException {
        isSecure(new DeleteObjectCommand(this.account, httpClient, defaultAccess, getObject("objectName")), 204);
    }
}