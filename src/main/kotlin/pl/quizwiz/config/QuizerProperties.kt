package pl.quizwiz.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "quizer")
data class QuizerProperties(
    val quizTimeInSeconds: Int,
    val pdfName: String,
    val questionsImgFolder: String,
    val resourcesFolder: String,
    val quizQuestionNumber: Int,
    val quizPassThreshold: Int
)