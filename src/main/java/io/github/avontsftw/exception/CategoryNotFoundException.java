package io.github.avontsftw.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super("Category 'Tickets' not found, please create it!");
    }
}
