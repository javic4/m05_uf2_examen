package cat.copernic.jmendezv

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.time.Duration
import java.util.stream.Stream
import kotlin.math.sqrt

internal class MainKtTest {
    companion object {

    @BeforeAll
    @JvmStatic
    fun init() {
        println("Iniciando tests...")
    }

    @AfterAll
    @JvmStatic
    fun end() {
        println("Finalizando tests...")
    }
}

    @ParameterizedTest
    @CsvSource("70.0,19.80,78.8, 500.0,1.80,320.0")
    @DisplayName("imc: Test parametizado CSV")
    fun imcTestCSV(weight: Double, height: Double, expected: Double) {
        Assertions.assertEquals(expected, IMC(weight, height), 0.1)
    }

    @ParameterizedTest
    @CsvSource("4.0,5.0,1.0,-0.129171,-3.87083, 8.0,10.0,4.0,-0.585786,-3.41421")
    @DisplayName("equSegundoGrado: Test parametizado CSV")
    fun equSegundoGradoTest(a: Double, b: Double, c: Double, expected1: Double, expected2: Double) {
        val results = equSegundoGrado(a, b, c)
        Assertions.assertEquals(expected1, results.first, 0.0001)
        Assertions.assertEquals(expected2, results.second, 0.0001)
    }

    @DisplayName("Distancia entre dos puntos Test")
    fun distanciaEntre2PuntosTest(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(Punto(0.0, 0.0), Punto(1.0, 3.0), sqrt(4.0)),
            Arguments.of(Punto(0.0, 0.0), Punto(4.0, 5.0), 7.1),
        )
    }

    @DisplayName("Calculamos la pendiente")
    fun pendienteTest(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(Punto(0.0, 0.0), Punto(3.0, 8.0), 12.0),
            Arguments.of(Punto(0.0, 0.0), Punto(2.0, 6.0), 6.0),
        )
    }

    @Test
    @DisplayName("Calculamos el punto medio")
    fun puntoMedioTest() {
        assertEquals(Punto(4.0, 4.0), puntoMedio(Punto(2.0, 4.0), Punto(2.0, 0.0)))
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 4])
    @DisplayName("Trobar el major i el menor")
    fun maxMinTest(n: Int) {
        assertEquals(Pair(n, n * 4), maxMin(listOf(n * 2, n * 3, n * 4, n)))
    }


    @Test
    @DisplayName("Más cercano test Timeout")
    fun masCercanoTest() {
        Assertions.assertTimeout(Duration.ofMillis(100)) {
            masCercano(Punto(0.0, 0.0), Punto(1.0, 0.0), Punto(2.0, 0.0), Punto(3.0, 0.0), Punto(4.0, 0.0))
        }
    }
}