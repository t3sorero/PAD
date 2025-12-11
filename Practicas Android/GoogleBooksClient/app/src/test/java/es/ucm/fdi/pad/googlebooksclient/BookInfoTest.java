package es.ucm.fdi.pad.googlebooksclient;

import org.junit.Test;
import static org.junit.Assert.*;
import java.net.URL;
import java.util.List;

public class BookInfoTest {

    @Test
    public void testConstructor() throws Exception {
        URL url = new URL("https://books.google.com/books?id=test");
        BookInfo book = new BookInfo("Test Title", "Test Author", url);

        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthors());
        assertEquals(url, book.getInfoLink());
    }

    @Test
    public void testConstructor_nullUrl() {
        BookInfo book = new BookInfo("Title", "Author", null);

        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthors());
        assertNull(book.getInfoLink());
    }

    @Test
    public void testFromJsonResponse_validResponse() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Test Book\",\"authors\":[\"Author One\",\"Author Two\"],\"infoLink\":\"https://books.google.com/books?id=123\"}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
        assertEquals("Author One, Author Two", result.get(0).getAuthors());
        assertNotNull(result.get(0).getInfoLink());
    }

    @Test
    public void testFromJsonResponse_noAuthors() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Magazine Title\",\"infoLink\":\"https://books.google.com/books?id=456\"}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertEquals("Sin autores", result.get(0).getAuthors());
    }

    @Test
    public void testFromJsonResponse_noTitle() {
        String json = "{\"items\":[{\"volumeInfo\":{\"authors\":[\"Test Author\"]}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertEquals("Sin título", result.get(0).getTitle());
    }

    @Test
    public void testFromJsonResponse_emptyResponse() {
        List<BookInfo> result = BookInfo.fromJsonResponse("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFromJsonResponse_nullResponse() {
        List<BookInfo> result = BookInfo.fromJsonResponse(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFromJsonResponse_noItems() {
        String json = "{\"totalItems\":0}";
        List<BookInfo> result = BookInfo.fromJsonResponse(json);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFromJsonResponse_invalidJson() {
        List<BookInfo> result = BookInfo.fromJsonResponse("invalid json");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFromJsonResponse_multipleItems() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Book 1\",\"authors\":[\"A1\"]}},{\"volumeInfo\":{\"title\":\"Book 2\",\"authors\":[\"A2\"]}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(2, result.size());
    }

    @Test
    public void testFromJsonResponse_malformedUrl() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Test\",\"infoLink\":\"not valid url\"}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertNull(result.get(0).getInfoLink());
    }

    @Test
    public void testFromJsonResponse_noInfoLink() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Book\",\"authors\":[\"Author\"]}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertNull(result.get(0).getInfoLink());
    }

    @Test
    public void testFromJsonResponse_emptyAuthorsArray() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Book\",\"authors\":[]}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals(1, result.size());
        assertEquals("", result.get(0).getAuthors());
    }

    @Test
    public void testFromJsonResponse_singleAuthor() {
        String json = "{\"items\":[{\"volumeInfo\":{\"title\":\"Book\",\"authors\":[\"Single Author\"]}}]}";

        List<BookInfo> result = BookInfo.fromJsonResponse(json);

        assertEquals("Single Author", result.get(0).getAuthors());
    }
}