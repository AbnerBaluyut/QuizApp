package com.interactive.quizapp.domain.usecase

import com.interactive.quizapp.data.local.entities.QuestionEntity
import com.interactive.quizapp.data.mapper.toEntity
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.repository.QuizRepository
import javax.inject.Inject

class SaveQuestionsUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    suspend operator fun invoke() {

        val questions = listOf(
            // 🧠 Category: General Knowledge
            QuestionEntity(1, "What is the capital of France?", listOf("Paris", "London", "Berlin", "Rome"), 0, "General Knowledge", false),
            QuestionEntity(2, "Which planet is known as the Red Planet?", listOf("Earth", "Venus", "Mars", "Jupiter"), 2, "General Knowledge", false),
            QuestionEntity(3, "Who painted the Mona Lisa?", listOf("Van Gogh", "Picasso", "Da Vinci", "Rembrandt"), 2, "General Knowledge", false),
            QuestionEntity(4, "What is the largest mammal?", listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1, "General Knowledge", false),
            QuestionEntity(5, "Which language has the most native speakers?", listOf("English", "Spanish", "Mandarin", "Hindi"), 2, "General Knowledge", false),
            QuestionEntity(6, "How many continents are there?", listOf("5", "6", "7", "8"), 2, "General Knowledge", false),
            QuestionEntity(7, "What is the hardest natural substance?", listOf("Gold", "Iron", "Diamond", "Granite"), 2, "General Knowledge", false),
            QuestionEntity(8, "Which ocean is the largest?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3, "General Knowledge", false),
            QuestionEntity(9, "How many legs does a spider have?", listOf("6", "8", "10", "12"), 1, "General Knowledge", false),
            QuestionEntity(10, "What gas do plants absorb?", listOf("Oxygen", "Hydrogen", "Carbon Dioxide", "Nitrogen"), 2, "General Knowledge", false),

            // 📚 Category: Science
            QuestionEntity(11, "What is H2O?", listOf("Oxygen", "Hydrogen", "Water", "Carbon Dioxide"), 2, "Science", false),
            QuestionEntity(12, "Which organ pumps blood?", listOf("Liver", "Brain", "Heart", "Lungs"), 2, "Science", false),
            QuestionEntity(13, "What planet is closest to the sun?", listOf("Mercury", "Venus", "Earth", "Mars"), 0, "Science", false),
            QuestionEntity(14, "Which gas do humans exhale?", listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"), 1, "Science", false),
            QuestionEntity(15, "What is the chemical symbol for gold?", listOf("Go", "Gd", "Au", "Ag"), 2, "Science", false),
            QuestionEntity(16, "What does DNA stand for?", listOf("Deoxyribonucleic Acid", "Dioxynuclear Acid", "Ribonucleic Acid", "Deoxyneural Atom"), 0, "Science", false),
            QuestionEntity(17, "How many bones in adult body?", listOf("206", "208", "210", "212"), 0, "Science", false),
            QuestionEntity(18, "Which part of plant makes food?", listOf("Root", "Stem", "Leaf", "Flower"), 2, "Science", false),
            QuestionEntity(19, "Speed of light?", listOf("300 km/s", "3000 km/s", "300,000 km/s", "30,000 km/s"), 2, "Science", false),
            QuestionEntity(20, "Which vitamin from sunlight?", listOf("A", "B", "C", "D"), 3, "Science", false),

            // 🌍 Category: Geography
            QuestionEntity(21, "Largest country by area?", listOf("USA", "China", "Russia", "Canada"), 2, "Geography", false),
            QuestionEntity(22, "Mount Everest is in?", listOf("India", "China", "Nepal", "Pakistan"), 2, "Geography", false),
            QuestionEntity(23, "Which country has most islands?", listOf("Canada", "Philippines", "Indonesia", "Sweden"), 3, "Geography", false),
            QuestionEntity(24, "Smallest country?", listOf("Monaco", "Malta", "Vatican City", "Liechtenstein"), 2, "Geography", false),
            QuestionEntity(25, "Which river is longest?", listOf("Nile", "Amazon", "Yangtze", "Mississippi"), 0, "Geography", false),
            QuestionEntity(26, "Which desert is largest?", listOf("Sahara", "Gobi", "Antarctica", "Arabian"), 2, "Geography", false),
            QuestionEntity(27, "Tokyo is in?", listOf("South Korea", "China", "Japan", "Thailand"), 2, "Geography", false),
            QuestionEntity(28, "Which continent is Egypt in?", listOf("Asia", "Africa", "Europe", "South America"), 1, "Geography", false),
            QuestionEntity(29, "Great Barrier Reef is in?", listOf("Brazil", "Australia", "Mexico", "Indonesia"), 1, "Geography", false),
            QuestionEntity(30, "Which country is Berlin capital of?", listOf("France", "Austria", "Germany", "Poland"), 2, "Geography", false),

            // 💻 Category: Technology
            QuestionEntity(31, "Who founded Microsoft?", listOf("Steve Jobs", "Elon Musk", "Bill Gates", "Mark Zuckerberg"), 2, "Technology", false),
            QuestionEntity(32, "What does CPU stand for?", listOf("Central Processing Unit", "Control Panel Unit", "Central Program Unit", "Computer Processing Unit"), 0, "Technology", false),
            QuestionEntity(33, "HTML is used for?", listOf("Data Analysis", "Web Pages", "Games", "Networking"), 1, "Technology", false),
            QuestionEntity(34, "Android OS is developed by?", listOf("Apple", "Microsoft", "Google", "Samsung"), 2, "Technology", false),
            QuestionEntity(35, "Main function of RAM?", listOf("Store long-term files", "Store temporary data", "Power the system", "Manage display"), 1, "Technology", false),
            QuestionEntity(36, "What is a URL?", listOf("Uniform Resource Locator", "Universal Link Reader", "Unified Route Link", "Unilateral Route List"), 0, "Technology", false),
            QuestionEntity(37, "Which is not a programming language?", listOf("Python", "HTML", "C++", "Java"), 1, "Technology", false),
            QuestionEntity(38, "What is AI?", listOf("Automated Intelligence", "Artificial Insight", "Artificial Intelligence", "Automated Interaction"), 2, "Technology", false),
            QuestionEntity(39, "What company makes iPhones?", listOf("Samsung", "Google", "Apple", "Nokia"), 2, "Technology", false),
            QuestionEntity(40, "What is Git used for?", listOf("Drawing", "Version Control", "Coding", "Hacking"), 1, "Technology", false),

            // 🏀 Category: Sports
            QuestionEntity(41, "How many players in soccer team?", listOf("9", "10", "11", "12"), 2, "Sports", false),
            QuestionEntity(42, "Who holds most Olympic golds?", listOf("Usain Bolt", "Michael Jordan", "Michael Phelps", "Simone Biles"), 2, "Sports", false),
            QuestionEntity(43, "What sport is Wimbledon for?", listOf("Cricket", "Golf", "Tennis", "Football"), 2, "Sports", false),
            QuestionEntity(44, "Basketball invented in which country?", listOf("USA", "Canada", "UK", "Germany"), 1, "Sports", false),
            QuestionEntity(45, "What is used in golf?", listOf("Ball", "Puck", "Shuttle", "Bat"), 0, "Sports", false),
            QuestionEntity(46, "Cricket has how many innings?", listOf("1", "2", "4", "6"), 1, "Sports", false),
            QuestionEntity(47, "Olympics every how many years?", listOf("2", "3", "4", "5"), 2, "Sports", false),
            QuestionEntity(48, "FIFA is for which sport?", listOf("Basketball", "Football", "Baseball", "Tennis"), 1, "Sports", false),
            QuestionEntity(49, "Who is Messi?", listOf("Basketballer", "Swimmer", "Footballer", "Golfer"), 2, "Sports", false),
            QuestionEntity(50, "Where were 2020 Olympics held?", listOf("Tokyo", "Paris", "Beijing", "London"), 0, "Sports", false),
        )


        repository.saveQuestions(questions)
    }
}