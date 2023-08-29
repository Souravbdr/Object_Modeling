package com.crio.jukebox.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.implementations.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CreateUserCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreateUserCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    UserService userService;

    @InjectMocks
    CreateUserCommand createUserCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("The load song command should print successfull")
    void CreateUserCommandExecuteTest(){
        User user = new User("UserName");
        when(userService.create(anyString())).thenReturn(user);
        createUserCommand.execute(List.of("CREATE-USER","UserName"));
        String expectedOutput = user.toString();
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(userService,times(1)).create(anyString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
