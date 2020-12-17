package dev.mazurkiewicz.florystyka.resource;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceServiceTest {

    private static byte[] expectedPngFileByteArray;
    private static byte[] expectedJpgFileByteArray;

    private ResourceService service = new ResourceService("src/test/resources", "/img");

    @BeforeAll
    private static void prepareExpectedImages() throws IOException {
        FileInputStream jpg = new FileInputStream("src/test/resources/img/test_img.jpg");
        expectedJpgFileByteArray = jpg.readAllBytes();
        FileInputStream png = new FileInputStream("src/test/resources/img/test_img.png");
        expectedPngFileByteArray = png.readAllBytes();
    }

    @Test
    void shouldReturnPngFileWhenCallGetImageWithExistFilename() {
        //given
        String filename = "test_img.png";

        //when
        byte[] result = service.getImage(filename);

        //then
        assertThat(result).isEqualTo(expectedPngFileByteArray);
    }

    @Test
    void shouldReturnJpgFileWhenCallGetImageWithExistFilename() {
        //given
        String filename = "test_img.jpg";

        //when
        byte[] result = service.getImage(filename);

        //then
        assertThat(result).isEqualTo(expectedJpgFileByteArray);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallGetImageWithNotExistFilename() {
        //given
        String filename = "not_exist.png";

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getImage(filename));

        //then
        assertThat(exception.getMessage()).isEqualTo("Image not_exist.png doesn't exist");
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallGetImageWithNotPermissionToImg() {
        //given
        //file with chmod = 600 and own to another user
        String filename = "not_readable.png";

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getImage(filename));

        //then
        assertThat(exception.getMessage()).startsWith("Cannot open image not_readable.png");
    }
}