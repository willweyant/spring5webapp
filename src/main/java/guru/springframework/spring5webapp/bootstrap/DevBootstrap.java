package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        final Author eric = new Author("Eric", "Evans");
        final Publisher harper = new Publisher("Harper Collins", "123 Test Street", "Richmond", "VA", "23219");
        final Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);

        authorRepository.save(eric);
        publisherRepository.save(harper);
        bookRepository.save(ddd);

        final Author rod = new Author("Rod", "Johnson");
        final Publisher wrox = new Publisher("Wrox", "345 Test Street", "Richmond", "VA", "23219");
        final Book noEJB = new Book("J2EE Development without EJB", "1234", wrox);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(wrox);
        bookRepository.save(noEJB);
    }
}
