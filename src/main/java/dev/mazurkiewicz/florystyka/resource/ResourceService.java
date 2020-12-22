package dev.mazurkiewicz.florystyka.resource;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;

@Service
public class ResourceService {

    private final String resourcesFolder;
    private final String questionsImgFolder;

    public ResourceService(@Value("${dev.mazurkiewicz.florystyka.resourcesFolder}") String resourcesFolder,
                           @Value("${dev.mazurkiewicz.florystyka.questionsImgFolder}") String questionsImgFolder) {
        this.resourcesFolder = resourcesFolder;
        this.questionsImgFolder = questionsImgFolder;
    }

    public byte[] getImage(String filename) {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(resourcesFolder);
        if (!resourcesFolder.endsWith("/") && !questionsImgFolder.startsWith("/"))
            fullPath.append(File.separator);
        fullPath.append(questionsImgFolder);
        fullPath.append(File.separator);
        fullPath.append(filename);

        try {
            InputStream input = new FileInputStream(fullPath.toString());
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
