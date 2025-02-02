package com.NSpro.springJwt.service;


import com.NSpro.springJwt.repository.BookRepository;
import com.NSpro.springJwt.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public void addBook(Book book) {
        bookRepo.save(book);
    }

    public void deleteById(int id) {

        bookRepo.deleteById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Optional<?> updateBook(int id, Book bookDetails) {
        Optional<Book> existingBook = bookRepo.findById(id);
        if(bookDetails.getTitle().trim().isEmpty()){
            return Optional.of("book title is empty.");
        }if(bookDetails.getAuthor().trim().isEmpty()){
            return Optional.of("book author is empty.");
        }//if(bookDetails.getISBN().trim().isEmpty()){
        //return Optional.of("book ISBN is empty.");
        //}
        if (existingBook.isPresent()) {
            existingBook.get().setTitle(bookDetails.getTitle());
            existingBook.get().setAuthor(bookDetails.getAuthor());
            //existingBook.get().setISBN(bookDetails.getISBN());
            existingBook.get().setRackNumber(bookDetails.getRackNumber());
            existingBook.get().setAvailableCopies(bookDetails.getAvailableCopies());
            existingBook.get().setTotalCopies(bookDetails.getTotalCopies());
            existingBook.get().setImageLink(bookDetails.getImageLink());
            return Optional.of(bookRepo.save(existingBook.get()));
        }
        return Optional.of(("Book with id " + id + " not found"));
    }


    public boolean bookIsExist(int id) {
        boolean flag =bookRepo.findById(id).isPresent();
        return flag;
    }

    public boolean isTitleAvailable(String title) {
        return bookRepo.findByTitle(title) == null;
    }

    //public boolean isISBNAvailable(String ISBN) {
    //    return bookRepo.findByISBN(ISBN) == null;
    //}


}
