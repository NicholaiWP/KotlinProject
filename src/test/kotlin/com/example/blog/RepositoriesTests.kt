package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val findSkyrimNerd = User("fusRoDah", "Jake", "Sullivan")
        entityManager.persist(findSkyrimNerd)
        val article = Article("Skyrim", "beautiful game!", "Game has a lot of features, great graphics and many hours game time", findSkyrimNerd)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val findSkyrimNerd = User("fusRoDah", "Jake", "Sullivan")
        entityManager.persist(findSkyrimNerd)
        entityManager.flush()
        val user = userRepository.findByLogin(findSkyrimNerd.login)
        assertThat(user).isEqualTo(findSkyrimNerd)
    }
}