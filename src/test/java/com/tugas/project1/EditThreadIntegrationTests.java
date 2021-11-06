package com.tugas.project1;

/**
 *
 * @author Lenovo
 */
import com.tugas.project1.interfaces.ThreadInterface;
import com.tugas.project1.models.Category;
import com.tugas.project1.models.User;
import com.tugas.project1.models.Thread;
import com.tugas.project1.repositories.ThreadRepository;
import com.tugas.project1.services.ThreadService;
import java.util.List;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EditThreadIntegrationTests {

    @InjectMocks
    @Autowired
    ThreadService service;

    @MockBean //MockBean
    ThreadRepository repository;

    @Mock
    ThreadInterface threadInterface;

    @Test
    public void updateThreadTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("Testing Hello world 3");
            thread.setContent("ASKDJASKDJASKJDJSAKDKAJSDKJASD");
            thread.setCategory(cat);

            when(repository.save(thread)).thenReturn(thread);
            service.store(thread);

        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadWithoutTitleTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("");
            thread.setContent("ASKDJASKDJASKJDJSAKDKAJSDKJASD");
            thread.setCategory(cat);

            when(repository.save(thread))
                    .thenThrow(new IllegalArgumentException("Title of the thread cannot be null!"));
            service.store(thread);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Title of the thread cannot be null!", e.getMessage());
        }
    }

    @Test
    public void updateThreadWithoutContentTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("Testing Hello world 3");
            thread.setContent("");
            thread.setCategory(cat);

            when(repository.save(thread))
                    .thenThrow(new IllegalArgumentException("Content of the thread cannot be null!"));
            service.store(thread);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadContentWith10kCharsTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();
            String content = "" + RandomString.make(10000).toLowerCase();

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("Testing Hello world 3");
            thread.setContent("" + content);
            thread.setCategory(cat);

            when(repository.save(thread)).thenReturn(thread);
            service.store(thread);
            service.store(thread);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadContentMoreThan10kCharsTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();
            String content = "" + RandomString.make(10001).toLowerCase();

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("");
            thread.setContent("" + content);
            thread.setCategory(cat);

            when(repository.save(thread))
                    .thenThrow(new IllegalArgumentException("Content of the thread should less than 10k characters!"));
            
            service.store(thread);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadTitleWithSymbolsTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();
            String title = "H3ll0 w0rld thr33 ~!`@#$%^&*()";

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("" + title);
            thread.setContent("ASKDJASKDJASKJDJSAKDKAJSDKJASD");
            thread.setCategory(cat);

            when(repository.save(thread)).thenReturn(thread);
            service.store(thread);
            
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadContentNumericOnlyTest() throws Exception {

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();
            String content = "" + Math.random() * 5;

            user.setName("adly");
            user.setId(2);

            cat.setId(2);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("Testing Hello world 3");
            thread.setContent(content);
            thread.setCategory(cat);

            when(repository.save(thread)).thenReturn(thread);
            service.store(thread);

        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void updateThreadWithDifferentAuthorTest() throws Exception {

        Throwable e = null;
        String message = null;

        try {
            User user = new User();
            Thread thread = new Thread();
            Category cat = new Category();

            user.setName("qwerty");
            user.setId(1);

            cat.setId(1);

            thread.setId(28);
            thread.setUser(user);
            thread.setTitle("Qwerty's Hello world 3");
            thread.setContent("ASJDHASDHADHADADADAJDAJSDAJDJASDJ");
            thread.setCategory(cat);

            when(repository.save(thread))
                    .thenThrow(new Exception("User is not the author of the thread!"));

            service.store(thread);
        } catch (Exception ex) {
            Assertions.assertFalse(e instanceof Exception);
        }
    }

}
