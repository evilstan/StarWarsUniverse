package com.evilstan.starwarsuniverse

import com.evilstan.starwarsuniverse.domain.cache.FilmCache
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 3)
    }
    @Test
    fun testFilmCache() {
        assertEquals("Star Wars: Episode IV: Saving private Ryan", FilmCache("Saving private Ryan", 4).toString())
        assertEquals("Star Wars: Episode VI: ", FilmCache("", 6).toString())
        assertEquals("Star Wars: Episode : ", FilmCache("", 100).toString())
    }
}