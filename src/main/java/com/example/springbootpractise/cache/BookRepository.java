package com.example.springbootpractise.cache;

public interface BookRepository {

    Book getByIsbn(String isbn);

}