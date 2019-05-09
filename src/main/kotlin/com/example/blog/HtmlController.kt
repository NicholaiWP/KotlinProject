package com.example.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HtmlController(private val repository: ArticleRepository, private  val userRepository: UserRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Video game reviews!"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
                .findBySlug(slug)
                ?.render()
                ?: throw IllegalArgumentException("Wrong article slug provided")
        model["wordCount"] = wordCounter(article.content)
        model["lixNum"] = article.content.lix().toString() + " " + lixCategoryOutPut(article.content.lix())
        model["title"] = article.title
        model["article"] = article
        return "article_new"
    }

    @GetMapping("/users")
    fun users(model: Model): String {
        model["users"] = userRepository.findAll().map { it }
        return "users"
    }

    @GetMapping("/sequences")
    fun sequences(model: Model): String {
        return "sequences"
    }

    @PostMapping("/sequences")
    fun sequenceSubmit(@ModelAttribute myForm: MyForm):ModelAndView{
        val mv = ModelAndView()
        mv.addObject("tal", myForm.tal)
        mv.addObject("amount", myForm.amount)
        println("number from form is: " +myForm.tal)
        println("amount from form is: " +myForm.amount)
        val tal = myForm.tal
        val amount = myForm.amount

        mv.viewName = "sequences"

        val seq11 = generateSequence(tal){it + tal}

        mv.addObject("result", seq11.take(amount).joinToString())

        return mv
    }

    @GetMapping("/calculator")
    fun calc(model: Model): String {

        return "calculator"
    }

    @PostMapping("/calculator")
    fun calcOutPut(@ModelAttribute calculator: Calculator):ModelAndView{
        val mv2 = ModelAndView()

        mv2.addObject("numOne", calculator.numOne)
        mv2.addObject("numTwo", calculator.numTwo)

        mv2.viewName = "calculator"

        mv2.addObject("plusResult", calculator.plus(calculator))
        mv2.addObject("divResult", calculator.div(calculator))
        mv2.addObject("minusResult", calculator.minus(calculator))
        mv2.addObject("timesResult", calculator.times(calculator))
        mv2.addObject("moduloResult", calculator.rem(calculator))

        return mv2
    }

    //prints lix category after lix number based on 'content' text difficulty
    fun lixCategoryOutPut(myVal: Int):String{
       return when(myVal) {
             0 -> "(This number is due to no punctuation)"
            in 0..24 -> "(Easy for all readers)"
            in 25..34 -> "(Easy for adept readers)"
            in 35..44 -> "(Medium reading difficulty)"
            in 45..54 -> "(hard reading difficulty)"
           else -> "(Very hard reading difficulty)"
        }
    }
        //finds amount of words, converts the parameter into lowercase characters
        //then splits the string using regex and returns the count of words in a string.
    fun wordCounter(userInputString: String):  Int {
        return userInputString.toLowerCase()
                .split(Regex(" "))
                .count()
    }

    fun Article.render() = RenderedArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format()
    )

    data class RenderedArticle(
            val slug: String,
            val title: String,
            val headline: String,
            val content: String,
            val author: User,
            val addedAt: String)

}