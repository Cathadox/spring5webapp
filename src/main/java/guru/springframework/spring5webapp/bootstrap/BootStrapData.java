package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository,
      BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) {

    Author eric = new Author("Eric", "Evans");
    Book ericsBook = new Book("Domain Driven Desing", "87318241");
    Publisher publisher = new Publisher("Some Inc", "Varazdinska 20", "Kumanovo", "1300");
    publisherRepository.save(publisher);

    eric.getBooks().add(ericsBook);
    ericsBook.getAuthors().add(eric);
    ericsBook.setPublisher(publisher);
    publisher.getBooks().add(ericsBook);

    authorRepository.save(eric);
    bookRepository.save(ericsBook);
    publisherRepository.save(publisher);

    Author rod = new Author("Rod", "Johnson");
    Book rodsBook = new Book("J2EE", "73812731");

    rod.getBooks().add(rodsBook);
    rodsBook.getAuthors().add(rod);
    publisher.getBooks().add(rodsBook);

    authorRepository.save(rod);
    bookRepository.save(rodsBook);
    publisherRepository.save(publisher);

    System.out.println("Bootstrap data initialized");
    System.out.println("Number of books: " + bookRepository.count());
    System.out.println("Number of authors: " + authorRepository.count());
    System.out.println("Number of publishers: " + publisherRepository.count());
    System.out.println("Number of published books: " + publisher.getBooks().size());


  }
}
