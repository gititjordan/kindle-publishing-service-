package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import javax.inject.Inject;

public class BookPublishTask implements Runnable{
    private final BookPublishRequestManager manager;
    private final PublishingStatusDao statusDao;
    private final CatalogDao catalogDao;

    @Inject
    public BookPublishTask(BookPublishRequestManager manager, PublishingStatusDao statusDao,
                           CatalogDao catalogDao) {
        this.manager = manager;
        this.statusDao = statusDao;
        this.catalogDao = catalogDao;
    }

    @Override
    public void run() {
        BookPublishRequest bookPublishRequest = manager.getBookPublishRequestToProcess();

        if(bookPublishRequest == null) {
            return;
        }

        PublishingStatusItem item = statusDao.setPublishingStatus
                (bookPublishRequest.getPublishingRecordId(),
                        PublishingRecordStatus.IN_PROGRESS,
                        bookPublishRequest.getBookId());

        KindleFormattedBook kindleFormattedBook = KindleFormatConverter.format(bookPublishRequest);

        try {
            CatalogItemVersion book = catalogDao.createOrUpdateBook(kindleFormattedBook);
            item = statusDao.setPublishingStatus
                    (bookPublishRequest.getPublishingRecordId(),
                            PublishingRecordStatus.SUCCESSFUL,
                            book.getBookId());

        } catch (Exception e) {
            item = statusDao.setPublishingStatus
                    (bookPublishRequest.getPublishingRecordId(),
                            PublishingRecordStatus.FAILED,
                            bookPublishRequest.getBookId());
        }

    }
}
