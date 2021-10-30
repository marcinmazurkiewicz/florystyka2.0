//package dev.mazurkiewicz.florystyka.resource;
//
//import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ResourceController.class)
//class ResourceControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private ResourceService service;
//
//    private static byte[] expectedPngFileByteArray;
//    private static byte[] expectedJpgFileByteArray;
//
//    @BeforeAll
//    private static void prepareExpectedImages() {
//        try (FileInputStream jpg = new FileInputStream("src/test/resources/img/test_img.jpg");
//             FileInputStream png = new FileInputStream("src/test/resources/img/test_img.png")) {
//            expectedJpgFileByteArray = jpg.readAllBytes();
//            expectedPngFileByteArray = png.readAllBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void shouldReturnByteArrayOfPngFileWhenCallGetImageWithPngFilename() throws Exception {
//        String filename = "test_img.png";
//        when(service.getImage(filename)).thenReturn(expectedPngFileByteArray);
//
//        mvc.perform(get("/resources/img/" + filename))
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", MediaType.IMAGE_PNG_VALUE))
//                .andExpect(header().string("Content-Length", String.valueOf(expectedPngFileByteArray.length)))
//                .andExpect(content().bytes(expectedPngFileByteArray));
//    }
//
//    @Test
//    void shouldReturnByteArrayOfJpgFileWhenCallGetImageWithPngFilename() throws Exception {
//        String filename = "test_img.jpg";
//        when(service.getImage(filename)).thenReturn(expectedJpgFileByteArray);
//
//        mvc.perform(get("/resources/img/" + filename))
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", MediaType.IMAGE_JPEG_VALUE))
//                .andExpect(header().string("Content-Length", String.valueOf(expectedJpgFileByteArray.length)))
//                .andExpect(content().bytes(expectedJpgFileByteArray));
//    }
//
//    @Test
//    void shouldReturn404StatusFileWhenCallGetImageWithNonExistFilename() throws Exception {
//        String filename = "not_exist.png";
//        when(service.getImage(filename)).thenThrow(ResourceNotFoundException.class);
//
//        mvc.perform(get("/resources/img/" + filename))
//                .andExpect(status().isNotFound());
//    }
//}