package testmodel

import fruits.model.Fruit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/***** Test Cases for Fruit *****/

class TestUsageFruit {

    @Test
    fun `test0 - compareTo uses the alphabetical order of names`() {
        assertTrue(Fruit("Abricot", 1.0, 1) < Fruit("Banane", 1.0, 1))
        assertTrue(Fruit("Banane", 1.0, 1) > Fruit("Abricot", 1.0, 1))
        assertEquals(0, Fruit("Kiwi", 1.0, 1).compareTo(Fruit("Kiwi", 9.0, 9)))
    }

    @Test
    fun `test1 - equals tolerates a price gap of 0,01 at most`() {
        assertEquals(Fruit("Kiwi", 4.50, 10), Fruit("Kiwi", 4.505, 10))
        assertNotEquals(Fruit("Kiwi", 4.50, 10), Fruit("Kiwi", 4.52, 10))
    }

    /* ---------- construction et propriétés ---------- */

    @Test
    fun `test2 - a fruit exposes its name, price and quantity`() {
        val fruit = Fruit("Kiwi", 4.5, 10)
        assertEquals("Kiwi", fruit.nom)
        assertEquals(4.5, fruit.prix)
        assertEquals(10, fruit.quantite)
    }

    @Test
    fun `test3 - the quantity can be modified`() {
        val fruit = Fruit("Kiwi", 4.5, 10)
        fruit.quantite = 25
        assertEquals(25, fruit.quantite)
    }

    @Test
    fun `test4 - copy keeps the unchanged properties`() {
        val fruit = Fruit("Kiwi", 4.5, 10).copy(quantite = 3)
        assertEquals(Fruit("Kiwi", 4.5, 3), fruit)
    }

    /* ---------- compareTo ---------- */

    @Test
    fun `test5 - compareTo is antisymmetric`() {
        val a = Fruit("Abricot", 1.0, 1)
        val b = Fruit("Banane", 1.0, 1)
        assertTrue(a.compareTo(b) < 0)
        assertTrue(b.compareTo(a) > 0)
    }

    @Test
    fun `test6 - compareTo is transitive`() {
        val a = Fruit("Abricot", 1.0, 1)
        val b = Fruit("Banane", 1.0, 1)
        val c = Fruit("Cerise", 1.0, 1)
        assertTrue(a < b)
        assertTrue(b < c)
        assertTrue(a < c)
    }

    @Test
    fun `test7 - compareTo ignores price and quantity`() {
        assertTrue(Fruit("Abricot", 99.0, 99) < Fruit("Banane", 1.0, 1))
        assertEquals(0, Fruit("Kiwi", 1.0, 1).compareTo(Fruit("Kiwi", 99.0, 99)))
    }

    @Test
    fun `test8 - compareTo compares names character by character`() {
        assertTrue(Fruit("Fraise", 1.0, 1) < Fruit("Framboise", 1.0, 1))
        assertTrue(Fruit("Pomme", 1.0, 1) < Fruit("Pomme verte", 1.0, 1))
    }

    @Test
    fun `test9 - compareTo gives the same order as the names`() {
        assertTrue(Fruit("Pomme", 1.0, 1) < Fruit("Pêche", 1.0, 1))
        assertTrue(Fruit("Melon", 1.0, 1) < Fruit("Mûre", 1.0, 1))
    }

    @Test
    fun `test10 - sorting fruits gives the alphabetical order`() {
        val fruits = listOf(Fruit("Poire", 1.0, 1), Fruit("Abricot", 2.0, 2), Fruit("Kiwi", 3.0, 3))
        assertEquals(listOf("Abricot", "Kiwi", "Poire"), fruits.sorted().map { it.nom })
    }

    /* ---------- equals ---------- */

    @Test
    fun `test11 - equals is reflexive and symmetric`() {
        val a = Fruit("Kiwi", 4.50, 10)
        val b = Fruit("Kiwi", 4.505, 10)
        assertEquals(a, a)
        assertEquals(a, b)
        assertEquals(b, a)
    }

    @Test
    fun `test12 - equals is false for another name`() {
        assertNotEquals(Fruit("Kiwi", 4.5, 10), Fruit("Pomme", 4.5, 10))
    }

    @Test
    fun `test13 - equals is false for another quantity`() {
        assertNotEquals(Fruit("Kiwi", 4.5, 10), Fruit("Kiwi", 4.5, 11))
    }

    @Test
    fun `test14 - equals is false for a price gap above 0,01`() {
        assertNotEquals(Fruit("Kiwi", 4.5, 10), Fruit("Kiwi", 4.6, 10))
        assertNotEquals(Fruit("Kiwi", 4.6, 10), Fruit("Kiwi", 4.5, 10))
    }

    @Test
    fun `test15 - equals is false for null and for another type`() {
        val fruit = Fruit("Kiwi", 4.5, 10)
        assertNotEquals(fruit, null)
        assertFalse(fruit.equals("Kiwi"))
    }

    /* ---------- sérialisation JSON ---------- */

    @Test
    fun `test16 - a fruit survives a JSON round trip`() {
        val fruit = Fruit("Kiwi", 4.5, 10)
        val json = Json.encodeToString(fruit)
        assertEquals(fruit, Json.decodeFromString<Fruit>(json))
    }

    @Test
    fun `test17 - a fruit is decoded from JSON properties`() {
        val fruit = Json.decodeFromString<Fruit>("""{"nom": "Kiwi", "quantite": 10, "prix": 4.5}""")
        assertEquals(Fruit("Kiwi", 4.5, 10), fruit)
    }
}
