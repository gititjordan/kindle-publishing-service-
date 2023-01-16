package com.amazon.ata.kindlepublishingservice.publishing;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BookPublishRequestManager {
    private final Queue<BookPublishRequest> bookPublishRequestQueue;

    @Inject
    public BookPublishRequestManager(Queue<BookPublishRequest> bookPublishRequestQueue ) {
        this.bookPublishRequestQueue = bookPublishRequestQueue;

    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return bookPublishRequestQueue.poll();

    }
    public void addBookPublishRequest(BookPublishRequest publishRequest) {
        bookPublishRequestQueue.add(publishRequest);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPublishRequestManager that = (BookPublishRequestManager) o;
        return bookPublishRequestQueue.equals(that.bookPublishRequestQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookPublishRequestQueue);
    }

    @Override
    public String toString() {
        return "BookPublishRequestManager{" +
                "bookPublishRequestList=" + bookPublishRequestQueue +
                '}';
    }
}
