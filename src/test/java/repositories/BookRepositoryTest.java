package repositories;

import com.learning.atypon.spring.mvc.domain.entity.BookEntity;
import com.learning.atypon.spring.mvc.repository.BookRepository;
import com.learning.atypon.spring.mvc.converter.BookConverter;
import com.learning.atypon.spring.mvc.domain.dto.BookDTO;
import com.learning.atypon.spring.mvc.exception.BookNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")

public class BookRepositoryTest {


    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findBookAfterSaving() {
        BookEntity book = new BookEntity();
        book.setTitle("A walk to remember");

        bookRepository.save(book);

        List<BookEntity> books = bookRepository.findAll();

        assertEquals(1, books.size());
        assertEquals("A walk to remember", books.get(0).getTitle());
     }

    @Test
    public void updateBookAfterSaving() {

        BookEntity book = new BookEntity();
        book.setTitle("Harry Potter");
        bookRepository.save(book);

        book.setTitle("Harry Potter2");
        bookRepository.save(book);

        List<BookEntity> books = bookRepository.findAll();

        assertEquals(1, books.size());
        assertEquals("Harry Potter2", books.get(0).getTitle());
      }

    @Test
    public void findByTitle() {

        BookEntity book = new BookEntity();
        book.setTitle("World of Warcraft");
        bookRepository.save(book);

        List<BookEntity> book2 = bookRepository.findByTitleContaining("World of Warcraft");

        assertNotNull(book2.get(0));
    }
}