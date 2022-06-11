package com.amazon.ata.kindlepublishingservice.models.response;

import com.amazon.ata.kindlepublishingservice.models.requests.RemoveBookFromCatalogRequest;

import java.util.Objects;

public class RemoveBookFromCatalogResponse {
    private String bookId;

    public RemoveBookFromCatalogResponse() {}

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveBookFromCatalogResponse that = (RemoveBookFromCatalogResponse) o;
        return Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    public RemoveBookFromCatalogResponse(RemoveBookFromCatalogResponse.Builder builder) {
        this.bookId = builder.bookId;
    }

    public static RemoveBookFromCatalogResponse.Builder builder() {return new RemoveBookFromCatalogResponse.Builder();}

    public static final class Builder {
        private String bookId;

        private Builder() {

        }

        public RemoveBookFromCatalogResponse.Builder withBookId(String bookIdToUse) {
            this.bookId = bookIdToUse;
            return this;
        }

        public RemoveBookFromCatalogResponse build() { return new RemoveBookFromCatalogResponse(this); }
    }
}
