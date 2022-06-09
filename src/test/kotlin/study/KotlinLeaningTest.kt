package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KotlinLeaningTest {

    @Test
    internal fun `확장 함수 Extension functions`() {
        assertThat("Kotlin".last()).isEqualTo('n')
        assertThat("Kotlin".lastChar()).isEqualTo('n')
    }

    @Test
    internal fun `중위 표기 Infix notation`() {
        println(1.to("one"))
        println(1 to "one")
        assertThat("Kotlin".last()).isEqualTo('n')
        assertThat("Kotlin".lastChar()).isEqualTo('n')
    }

    @Test
    internal fun `연산자 오버로딩 Operator overloading`() {
        val point = Point(0, 1) + (Point(1, 2))
        assertThat(point.x).isEqualTo(1)
        assertThat(point.y).isEqualTo(3)
    }

    @Test
    internal fun `get 메서드에 대한 관례 ndexed access operator`() {
        val names = listOf("jade", "koh")
        println(names.get(0))
        println(names[0])
    }

    @Test
    internal fun `람다를 괄호 밖으로 빼내는 관례 Passing a lambda to the last parameter`() {
        check(true, { -> "Check failed." })
        check(true) { "Check failed." }
    }

    @Test
    internal fun `수신 객체 지정 람다 Lambda with receiver`() {
        val sb = StringBuilder()
        sb.apply {
            this.append("Yes")
            append("No")
        }
    }

    @Test
    internal fun `infix 테스트`() {
        assertThat("test" level 10).isEqualTo("test::10")
    }

    @Test
    internal fun `sealed 클래스`() {
        val fatDog = Animal.Dog("fat dog", 20)
        val slimDog = Animal.Dog("slim dog", 10)
        val cat = Animal.Cat("navi")
        val pig = Animal.Pig(50)

        assertThat(fatDog.myName()).isEqualTo("dog:fat dog")
        assertThat(slimDog.myName()).isEqualTo("dog:slim dog")
        assertThat(slimDog.weight).isEqualTo(10)
        assertThat(cat.name).isEqualTo("navi")
        assertThat(pig.myName()).isEqualTo("pig:none")
        assertThat(pig.weight).isEqualTo(50)
    }
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

fun String.lastChar(): Char {
    return get(length - 1)
}

infix fun String.level(level: Int): String {
    return "$this::$level"
}

sealed class Animal(open val name: String) {

    constructor() : this("none")

    data class Dog(override val name: String, val weight: Int) : Animal(name)
    data class Cat(override val name: String) : Animal(name)
    data class Pig(val weight: Int) : Animal()

    fun myName() = when (this) {
        is Dog -> {
            "dog:$name"
        }
        is Cat -> {
            "cat:$name"
        }
        is Pig -> {
            "pig:$name"
        }
    }
}