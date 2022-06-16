package dev.mazurkiewicz.quizer.resource;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;

@Service
public class ResourceService {

    private final QuizerProperties properties;

    public ResourceService(QuizerProperties properties) {
        this.properties = properties;
    }

    public byte[] getImage(String filename) {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(properties.resourcesFolder());
        if (!properties.resourcesFolder().endsWith(File.separator) && !properties.questionsImgFolder().startsWith(File.separator))
            fullPath.append(File.separator);
        fullPath.append(properties.questionsImgFolder());
        fullPath.append(File.separator);
        fullPath.append(filename);


        try (InputStream input = new FileInputStream(fullPath.toString())) {
            return input.readAllBytes();
        } catch (FileNotFoundException e) {
            String msg = (e.getMessage().contains("Permission denied")) ?
                    String.format("Cannot open image %s", filename) :
                    String.format("Image %s doesn't exist", filename);
            throw new ResourceNotFoundException(msg);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE, String.format("Cannot access file %s: %s", filename, e.getMessage()));
        }
    }
}
