package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.kindlepublishingservice.converters.CatalogItemConverter;
import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;
import com.amazon.ata.kindlepublishingservice.models.Book;
import com.amazon.ata.kindlepublishingservice.models.requests.RemoveBookFromCatalogRequest;
import com.amazon.ata.kindlepublishingservice.models.response.RemoveBookFromCatalogResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;

public class RemoveBookFromCatalogActivity {
    private CatalogDao catalogDao;

    @Inject
    RemoveBookFromCatalogActivity(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public RemoveBookFromCatalogResponse execute(RemoveBookFromCatalogRequest removeBookFromCatalogRequest) {
        CatalogItemVersion catalogItemVersion = new CatalogItemVersion();
        catalogItemVersion.setBookId(removeBookFromCatalogRequest.getBookId());

        catalogDao.removeBookFromCatalog(removeBookFromCatalogRequest.getBookId());

        return RemoveBookFromCatalogResponse.builder()
                .withBookId(catalogItemVersion.getBookId())
                .build();
    }
}
