package dev.mazurkiewicz.quizer.pdf;

public class PdfGeneratorExpectedTemplate {

    public static final String expectedHtmlWith10Questions = """
            <!DOCTYPE html>
            <html lang="pl">
            <head>
                <meta charset="utf-8"/>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                    }

                    table {
                        width: 100%;
                        display: table;
                        padding: 16px;
                        margin-top: 16px;
                        border-collapse: collapse;
                    }

                    tr {
                        page-break-inside: avoid;
                    }

                    td, th {
                        display: table-cell;
                        padding: 10px;
                        border: 1px solid #000;
                        font-size: 14px;
                        text-align: center;
                        page-break-inside: avoid;
                    }

                    .image {
                        display: block;
                        margin-left: auto;
                        margin-right: auto;
                        width: 50%;
                        page-break-inside: avoid;
                    }

                    .question-wrapper {
                        margin-top: 32px;
                        page-break-inside: avoid;
                    }

                    .answers-table {
                        page-break-before: always;
                        page-break-inside: avoid;
                        text-align: center;
                    }

                    .question {
                        font-weight: bold;
                    }

                    .site-info {
                        display: block;
                        font-size: 10px;
                        text-align: right
                    }

                    .doc-title {
                        display: block;
                        font-size: 24px;
                        text-align: center;
                        font-weight: bold;
                        padding-top: 60px;
                        margin-bottom: 16px;
                    }

                    .doc-subtitle {
                        font-size: 14px;
                        text-align: center;
                        font-weight: bold;
                    }
                </style>
            </head>
            <body>
            <header>
                <span class="site-info">https://egzamin-florystyka.pl</span>
                <h1 class="doc-title">Kwalifikacja zawodowa R.26</h1>
                <p class="doc-subtitle">Wykonywanie kompozycji florystycznych (test próbny)</p>
                <div class="question-wrapper">
                    <p class="question">
                        <span>1.</span>
                        <span>Question 1</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 1</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 1</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 1</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 1</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>2.</span>
                        <span>Question 2</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 2</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 2</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 2</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 2</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>3.</span>
                        <span>Question 3</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 3</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 3</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 3</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 3</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>4.</span>
                        <span>Question 4</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 4</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 4</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 4</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 4</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>5.</span>
                        <span>Question 5</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 5</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 5</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 5</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 5</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>6.</span>
                        <span>Question 6</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 6</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 6</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 6</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 6</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>7.</span>
                        <span>Question 7</span>
                    </p>
                    <img class="image" src="quest_7.png"/>
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 7</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 7</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 7</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 7</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>8.</span>
                        <span>Question 8</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 8</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 8</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 8</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 8</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>9.</span>
                        <span>Question 9</span>
                    </p>
                    <img class="image" src="quest_9.png"/>
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 9</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 9</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 9</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 9</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>10.</span>
                        <span>Question 10</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 10</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 10</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 10</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 10</span>
                    </p>
                </div>
                <div class="answers-table">
                    <h1>Klucz odpowiedzi</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <span>1.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                            <td>
                                <span>6.</span>
                            </td>
                            <td>
                                <span>C</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>2.</span>
                            </td>
                            <td>
                                <span>C</span>
                            </td>
                            <td>
                                <span>7.</span>
                            </td>
                            <td>
                                <span>D</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>3.</span>
                            </td>
                            <td>
                                <span>D</span>
                            </td>
                            <td>
                                <span>8.</span>
                            </td>
                            <td>
                                <span>A</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>4.</span>
                            </td>
                            <td>
                                <span>A</span>
                            </td>
                            <td>
                                <span>9.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>5.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                            <td>
                                <span>10.</span>
                            </td>
                            <td>
                                <span>C</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </header>
            </body>
            </html>""";

    public static final String expectedHtmlWith9Questions = """
            <!DOCTYPE html>
            <html lang="pl">
            <head>
                <meta charset="utf-8"/>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                    }

                    table {
                        width: 100%;
                        display: table;
                        padding: 16px;
                        margin-top: 16px;
                        border-collapse: collapse;
                    }

                    tr {
                        page-break-inside: avoid;
                    }

                    td, th {
                        display: table-cell;
                        padding: 10px;
                        border: 1px solid #000;
                        font-size: 14px;
                        text-align: center;
                        page-break-inside: avoid;
                    }

                    .image {
                        display: block;
                        margin-left: auto;
                        margin-right: auto;
                        width: 50%;
                        page-break-inside: avoid;
                    }

                    .question-wrapper {
                        margin-top: 32px;
                        page-break-inside: avoid;
                    }

                    .answers-table {
                        page-break-before: always;
                        page-break-inside: avoid;
                        text-align: center;
                    }

                    .question {
                        font-weight: bold;
                    }

                    .site-info {
                        display: block;
                        font-size: 10px;
                        text-align: right
                    }

                    .doc-title {
                        display: block;
                        font-size: 24px;
                        text-align: center;
                        font-weight: bold;
                        padding-top: 60px;
                        margin-bottom: 16px;
                    }

                    .doc-subtitle {
                        font-size: 14px;
                        text-align: center;
                        font-weight: bold;
                    }
                </style>
            </head>
            <body>
            <header>
                <span class="site-info">https://egzamin-florystyka.pl</span>
                <h1 class="doc-title">Kwalifikacja zawodowa R.26</h1>
                <p class="doc-subtitle">Wykonywanie kompozycji florystycznych (test próbny)</p>
                <div class="question-wrapper">
                    <p class="question">
                        <span>1.</span>
                        <span>Question 1</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 1</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 1</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 1</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 1</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>2.</span>
                        <span>Question 2</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 2</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 2</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 2</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 2</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>3.</span>
                        <span>Question 3</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 3</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 3</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 3</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 3</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>4.</span>
                        <span>Question 4</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 4</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 4</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 4</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 4</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>5.</span>
                        <span>Question 5</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 5</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 5</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 5</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 5</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>6.</span>
                        <span>Question 6</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 6</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 6</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 6</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 6</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>7.</span>
                        <span>Question 7</span>
                    </p>
                    <img class="image" src="quest_7.png"/>
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 7</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 7</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 7</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 7</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>8.</span>
                        <span>Question 8</span>
                    </p>
                   \s
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 8</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 8</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 8</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 8</span>
                    </p>
                </div>
                <div class="question-wrapper">
                    <p class="question">
                        <span>9.</span>
                        <span>Question 9</span>
                    </p>
                    <img class="image" src="quest_9.png"/>
                    <p>
                        <span>A.</span>
                        <span>Answer A for question 9</span>
                    </p>
                    <p>
                        <span>B.</span>
                        <span>Answer B for question 9</span>
                    </p>
                    <p>
                        <span>C.</span>
                        <span>Answer C for question 9</span>
                    </p>
                    <p>
                        <span>D.</span>
                        <span>Answer D for question 9</span>
                    </p>
                </div>
                <div class="answers-table">
                    <h1>Klucz odpowiedzi</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <span>1.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                            <td>
                                <span>6.</span>
                            </td>
                            <td>
                                <span>C</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>2.</span>
                            </td>
                            <td>
                                <span>C</span>
                            </td>
                            <td>
                                <span>7.</span>
                            </td>
                            <td>
                                <span>D</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>3.</span>
                            </td>
                            <td>
                                <span>D</span>
                            </td>
                            <td>
                                <span>8.</span>
                            </td>
                            <td>
                                <span>A</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>4.</span>
                            </td>
                            <td>
                                <span>A</span>
                            </td>
                            <td>
                                <span>9.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>5.</span>
                            </td>
                            <td>
                                <span>B</span>
                            </td>
                            <td>
                               \s
                            </td>
                            <td>
                               \s
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </header>
            </body>
            </html>""";

    public static final String expectedHtmlWithNoQuestions = """
            <!DOCTYPE html>
            <html lang="pl">
            <head>
                <meta charset="utf-8"/>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                    }

                    table {
                        width: 100%;
                        display: table;
                        padding: 16px;
                        margin-top: 16px;
                        border-collapse: collapse;
                    }

                    tr {
                        page-break-inside: avoid;
                    }

                    td, th {
                        display: table-cell;
                        padding: 10px;
                        border: 1px solid #000;
                        font-size: 14px;
                        text-align: center;
                        page-break-inside: avoid;
                    }

                    .image {
                        display: block;
                        margin-left: auto;
                        margin-right: auto;
                        width: 50%;
                        page-break-inside: avoid;
                    }

                    .question-wrapper {
                        margin-top: 32px;
                        page-break-inside: avoid;
                    }

                    .answers-table {
                        page-break-before: always;
                        page-break-inside: avoid;
                        text-align: center;
                    }

                    .question {
                        font-weight: bold;
                    }

                    .site-info {
                        display: block;
                        font-size: 10px;
                        text-align: right
                    }

                    .doc-title {
                        display: block;
                        font-size: 24px;
                        text-align: center;
                        font-weight: bold;
                        padding-top: 60px;
                        margin-bottom: 16px;
                    }

                    .doc-subtitle {
                        font-size: 14px;
                        text-align: center;
                        font-weight: bold;
                    }
                </style>
            </head>
            <body>
            <header>
                <span class="site-info">https://egzamin-florystyka.pl</span>
                <h1 class="doc-title">Kwalifikacja zawodowa R.26</h1>
                <p class="doc-subtitle">Wykonywanie kompozycji florystycznych (test próbny)</p>
               \s
                <div class="answers-table">
                    <h1>Klucz odpowiedzi</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                            <th>Nr pytania</th>
                            <th>Prawidłowa odpowiedź</th>
                        </tr>
                        </thead>
                        <tbody>
                       \s
                        </tbody>
                    </table>
                </div>
            </header>
            </body>
            </html>""";
}
