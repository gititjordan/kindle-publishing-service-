package com.amazon.ata.kindlepublishingservice.publishing;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Queue;

public class BookPublishRequestManager {
    private Queue<BookPublishRequest> bookPublishRequestQueue;

    @Inject
    public BookPublishRequestManager(Queue<BookPublishRequest> bookPublishRequestQueue ) {
        this.bookPublishRequestQueue = bookPublishRequestQueue;

    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return bookPublishRequestQueue.remove();

    }
    public void addBookPublishRequest(BookPublishRequest publishRequest) {
        bookPublishRequestQueue.add(publishRequest);
    }

}
