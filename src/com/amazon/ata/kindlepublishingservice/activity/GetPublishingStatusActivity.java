package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.converters.BookPublishRequestConverter;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.PublishingStatusNotFoundException;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;
import com.amazon.ata.kindlepublishingservice.models.requests.GetPublishingStatusRequest;
import com.amazon.ata.kindlepublishingservice.models.response.GetPublishingStatusResponse;
import com.amazon.ata.kindlepublishingservice.models.response.SubmitBookForPublishingResponse;
import com.amazon.ata.kindlepublishingservice.publishing.BookPublishRequest;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class GetPublishingStatusActivity {
    PublishingStatusDao publishingStatusDao;
    PublishingStatusRecord publishingStatusRecord;

    @Inject
    public GetPublishingStatusActivity(PublishingStatusDao publishingStatusDao) {
        this.publishingStatusDao = publishingStatusDao;
    }

    public GetPublishingStatusResponse execute(GetPublishingStatusRequest request) throws PublishingStatusNotFoundException {
        List<PublishingStatusItem> publishingStatusItems = publishingStatusDao.getPublishingStatus(request.getPublishingRecordId());

        List<PublishingStatusRecord> publishingStatusRecordList = new ArrayList<>();

        for (PublishingStatusItem publishingStatusItem: publishingStatusItems) {
            publishingStatusRecordList.add(new PublishingStatusRecord(publishingStatusItem.getStatus().toString(),
                    publishingStatusItem.getStatusMessage(),publishingStatusItem.getBookId()));

        }
        return GetPublishingStatusResponse.builder()
                .withPublishingStatusHistory(publishingStatusRecordList)
                .build();

    }
}
