package dev.mazurkiewicz.florystyka.resource;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ResourceService {

    @Value("${dev.mazurkiewicz.florystyka.resourcesFolder}")
    private String resourcesFolder;
    @Value("${dev.mazurkiewicz.florystyka.questionsImgFolder}")
    private String questionsImgFolder;

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
            throw new ResourceNotFoundException(String.format("Image %s doesn't exist", filename));
        } catch (IOException e) {
            throw new ResourceNotFoundException(String.format("Cannot read file %s: %s", filename, e.getMessage()));
        }
    }
}
