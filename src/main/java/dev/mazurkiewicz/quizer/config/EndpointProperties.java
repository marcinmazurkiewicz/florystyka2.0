package dev.mazurkiewicz.quizer.config;

public class EndpointProperties {
    public static final String QUESTIONS_ENDPOINT_MAIN = "/api/v2/questions";
    public static final String EXAMS_ENDPOINT_MAIN = "/api/v2/exams";
    public static final String QUESTIONS_ENDPOINT_RANDOM = "/random";
    public static final String QUESTIONS_ENDPOINT_EXAM = "/generate";
    public static final String QUESTIONS_INFO_ENDPOINT_MAIN = "/api/v2/info/questions";
    public static final String QUESTIONS_ENDPOINT_PDF = "/exam/pdf";
    public static final String SOLUTIONS_ENDPOINT_SINGLE = "/answer";
    public static final String SOLUTIONS_ENDPOINT_EXAM = "/solution";
    public static final String RESOURCES_ENDPOINT_MAIN = "/resources";
    public static final String RESOURCES_ENDPOINT_IMG = "/img/{filename}";
    public final static String RESOURCE_IMG_PREFIX = "resources/img";

}
