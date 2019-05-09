package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun lixOne(){
		//Passed
		val d = "Java is a very popular language used in all kinds of applications."
		assertThat(d.lix()).isEqualTo(37)
	}

	@Test
	fun lixTwo(){
		//for some reason this test fails, it expects 50, but the actual value is 51. I Suppose this have something to do
		//With my calculations, that a decimal gets rounded to a whole number as the other test is passed successfully using the
		//same function.
		val s = "Kotlin is a language that supports both imperative, functional and object oriented programming. The official site is full of documentation."
		assertThat(s.lix()).isEqualTo(50)
	}

}
