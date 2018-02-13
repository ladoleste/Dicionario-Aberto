package br.com.ladoleste.dicionarioaberto

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Definicao local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun addition_isCorrect() {

        val a = sum(5)
        val b = a(15)
        val c = b(0)

        assertEquals(20, c)
    }

    private fun sum(a: Int) = { b: Int -> { c: Int -> a + b + c } }

}
