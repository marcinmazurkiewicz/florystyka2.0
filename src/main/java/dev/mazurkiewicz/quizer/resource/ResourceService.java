package dev.mazurkiewicz.quizer.resource;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ResourceService {

    QuizerConfiguration quizerConfiguration;

    public byte[] getImage(String filename) {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(quizerConfiguration.resourcesFolder());
        if (!quizerConfiguration.resourcesFolder().endsWith(File.separator) && !quizerConfiguration.questionsImgFolder().startsWith(File.separator))
            fullPath.append(File.separator);
        fullPath.append(quizerConfiguration.questionsImgFolder());
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
