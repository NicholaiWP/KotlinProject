package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val skyrimNerd = userRepository.save(User(login = "fusRoDah", firstname = "Jake", lastname = "Sullivan", description = "User description: Adventures, loves RPGs", id = 1))
        val marioNerd = userRepository.save(User(login = "Luigi", firstname = "Bob", lastname = "Peterson", description = "User description: Loves mushrooms", id = 2 ))
        val callOfDutyNerd = userRepository.save(User(login = "007agent", firstname = "Thomas", lastname = "Andersen", description = "User description: His favourite gun is AK47", id = 3 ))
        val tester = userRepository.save(User(login = "testRun", firstname = "Tester", lastname = "Lawson", id = 4))

        articleRepository.save(Article(
                title = "Mario review",
                headline = "I love mario!!",
                content = "Mario is the best application.",
                author = marioNerd

        ))

        articleRepository.save(Article(
                title = "Skyrim review",
                headline = "beautiful game!",
                content = "Game has a lot of features, great graphics and many hours game time",
                author = skyrimNerd
        ))

        articleRepository.save(Article(
                title = "Call Of Duty review",
                headline = "Friendly fire sucks!",
                content = "The developers should permanently remove friendly-fire mechanics. It would please the community of shooting-game-loving players.",
              //  content = "Java is a very popular language used in all kinds of applications.",
                author = callOfDutyNerd
        ))

        articleRepository.save(Article(
                title = "Test review",
                headline = "Testing expected values, '12', 'medium difficulty'",
                content = "Java is a very popular language used in all kinds of applications.",
                author = tester
        ))

    }
}