package dev.mazurkiewicz.quizer.resource;

import dev.mazurkiewicz.quizer.config.ApplicationProperties;
import dev.mazurkiewicz.quizer.exception.FileTypeException;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResourceService {

    private final ApplicationProperties properties;

    public ResourceService(ApplicationProperties properties) {
        this.properties = properties;
    }

    public byte[] getImage(String filename) {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(getImageFolderPath());
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

    private String getImageFolderPath() {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(properties.getResourcesFolder());
        if (!properties.getResourcesFolder().endsWith(File.separator) && !properties.getQuestionsImgFolder().startsWith(File.separator))
            fullPath.append(File.separator);
        fullPath.append(properties.getQuestionsImgFolder());
        fullPath.append(File.separator);
        return fullPath.toString();
    }

    public Set<String> getListOfImages(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    private String generateFilename(MimeType mimeType) throws FileTypeException {
        Set<String> existingFilenames = getListOfImages(getImageFolderPath());
        String filename;
        do {
            filename = UUID.randomUUID().toString();
        } while(existingFilenames.contains(filename));
        if (MimeType.valueOf("image/png").equals(mimeType)) {
            filename += ".png";
        } else if (MimeType.valueOf("image/jpeg").equals(mimeType)) {
            filename += ".jpg";
        } else {
            throw new FileTypeException("Incorrect file format. Only png and jpg files are supported");
        }
        return filename;
    }

    public String saveQuestionImage(MultipartFile image) throws IOException, FileTypeException {
        StringBuilder path = new StringBuilder();
        String filename = generateFilename(MimeType.valueOf(Objects.requireNonNull(image.getContentType())));
        path.append(getImageFolderPath());
        path.append(filename);

        FileOutputStream fos = new FileOutputStream(path.toString());
        fos.write(image.getBytes());
        return filename;
    }


}
