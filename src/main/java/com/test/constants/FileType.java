package com.test.constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum FileType {

    @JsonProperty(value = "Document")
    DOCUMENT("Document"),

    @JsonProperty(value = "Image")
    IMAGE("Image"),

    @JsonProperty(value = "File")
    FILE("File"),

    @JsonProperty(value = "Executable")
    EXECUTABLE("Executable"),

    @JsonProperty(value = "Audio")
    AUDIO("Audio"),

    @JsonProperty(value = "Video")
    VIDEO("Video");

    private final String value;

    public static FileType getByValue(String value) {
        return Optional.ofNullable(value)
                .map(val -> Arrays.asList(values())
                        .stream()
                        .filter(e -> e.getValue().equals(val))
                        .findFirst()
                        .orElse(FILE))
                .orElse(FILE);

    }

    public static List<FileType> streamable() {
        return Arrays.asList(IMAGE, VIDEO, AUDIO);
    }

    public boolean isStreamable() {
        return streamable().contains(FileType.values()[ordinal()]);
    }

    public boolean isImage() {
        return IMAGE.equals(FileType.values()[ordinal()]);
    }

    public boolean isDocument() {
        return DOCUMENT.equals(FileType.values()[ordinal()]);
    }

    public boolean isVideo() {
        return VIDEO.equals(FileType.values()[ordinal()]);
    }

    public boolean isAudio() {
        return AUDIO.equals(FileType.values()[ordinal()]);
    }

    public boolean isFile() {
        return FILE.equals(FileType.values()[ordinal()]);
    }

    public boolean isExecutable() {
        return EXECUTABLE.equals(FileType.values()[ordinal()]);
    }
}
