package nl.tweeenveertig.openstack.command.container;

import nl.tweeenveertig.openstack.command.core.BaseCommandTest;
import nl.tweeenveertig.openstack.command.core.CommandExceptionError;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.when;

public class DeleteContainerCommandTest extends BaseCommandTest {

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void deleteContainerSuccess() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(204);
        new DeleteContainerCommand(httpClient, defaultAccess, account.getContainer("containerName")).call();
    }

    @Test
    public void deleteContainerDoesNotExist() throws IOException {
        checkForError(404, new DeleteContainerCommand(httpClient, defaultAccess, account.getContainer("containerName")), CommandExceptionError.CONTAINER_DOES_NOT_EXIST);
    }

    @Test
    public void deleteContainerNotEmpty() throws IOException {
        checkForError(409, new DeleteContainerCommand(httpClient, defaultAccess, account.getContainer("containerName")), CommandExceptionError.CONTAINER_NOT_EMPTY);
    }

    @Test
    public void unknownError() throws IOException {
        checkForError(500, new DeleteContainerCommand(httpClient, defaultAccess, account.getContainer("containerName")), CommandExceptionError.UNKNOWN);
    }
}