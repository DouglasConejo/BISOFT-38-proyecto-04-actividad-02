package comon.example.search
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class GrepTest {
    companion object {
        val sampleData = listOf(
            "123 abc",
            "abc 123",
            "123 ABC",
            "ABC 123"
        )
    }

    @Test
    fun shouldFindMatches() {
        val resultados = mutableListOf<String>()
        grep(sampleData, "[a-z]+") {
            resultados.add(it)
        }

        assertEquals(2, resultados.size)
        for (resultado in resultados) {
            assertContains(resultado, "abc")
        }
    }

    fun grep(data: List<String>, regex: String, action: (String) -> Unit) {
        val pattern = Regex(regex)
        data.forEach { line ->
            if (line.contains(pattern)) {
                action(line)
            }
        }
    }
}