# Práctica: Medición de tiempo de ejecución con `BufferedOutputStream`

## Objetivo

Generar un archivo de `n` bytes utilizando `BufferedOutputStream` en Java y medir el tiempo de ejecución del proceso con `System.nanoTime()`.

---


## Marco teórico breve

- **`FileOutputStream`**: escribe bytes directamente al disco, operación costosa si se hace byte por byte.
- **`BufferedOutputStream`**: almacena los bytes en un buffer interno (por defecto 8 KB) y los escribe al disco en bloques, reduciendo el número de accesos al sistema de archivos.
- **`System.nanoTime()`**: retorna el tiempo actual en nanosegundos con alta precisión; se usa para medir intervalos de tiempo.

---

## Instrucciones

### Paso 1. Crear el archivo Java

Crea un archivo llamado `EscrituraBuffered.java` en tu proyecto.

### Paso 2. Definir la cantidad de bytes a escribir

Dentro del método `Main`, declara una variable `n` con la cantidad de bytes que deseas escribir. Por ejemplo:

```java
int n = 1_000_000; // 1 millón de bytes (~1 MB)
```

> Puedes probar con distintos valores: 1_000, 100_000, 1_000_000, 10_000_000, etc.

### Paso 3. Definir la ruta del archivo

```java
String ruta = "archivo_salida.dat";
```

### Paso 4. Escribir con `BufferedOutputStream` y medir el tiempo

Utiliza el siguiente código como base:

```java
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscrituraBuffered {
    public static void parcial02.practica02.Main(String[] args) {
        int n = 1_000_000; // cantidad de bytes
        String ruta = "archivo_salida.dat";
        byte dato = 'A'; // byte que se escribirá repetidamente

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ruta))) {

            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                bos.write(dato);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: " + duracionNs + " ns (" + duracionMs + " ms)");

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
```

> **Nota:** El `try-with-resources` garantiza que el buffer se vacíe (`flush`) y se cierre al final. Esto es importante porque parte del tiempo puede corresponder al volcado final del buffer al disco.

### Paso 5. Compilar y ejecutar

Desde la terminal, en la carpeta donde está el archivo:

```bash
javac EscrituraBuffered.java
java EscrituraBuffered
```

### Paso 6. Registrar los resultados

Ejecuta el programa al menos **3 veces** para cada valor de `n` y anota los tiempos en una tabla como la siguiente:

| Tamaño (bytes) | Ejecución 1 (ms) | Ejecución 2 (ms) | Ejecución 3 (ms) | Promedio (ms) |
|----------------|------------------|------------------|------------------|---------------|
| 100,000          |                  |                  |                  |               |
| 1,000,000        |                  |                  |                  |               |
| 10,000,000      |                  |                  |                  |               |


### Paso 7. Comparar con escritura sin buffer

Para observar la diferencia, repite el experimento usando `FileOutputStream` directamente (sin `BufferedOutputStream`) y compara los tiempos:

```java
try (FileOutputStream fos = new FileOutputStream(ruta)) {
    long inicio = System.nanoTime();

    for (int i = 0; i < n; i++) {
        fos.write(dato);
    }

    long fin = System.nanoTime();
    long duracionNs = fin - inicio;
    double duracionMs = duracionNs / 1_000_000.0;

    System.out.println("Sin buffer -> Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## Análisis y preguntas para el reporte

1. ¿Qué diferencia observaste entre usar `BufferedOutputStream` y `FileOutputStream` directo? ¿Por qué ocurre?
2. ¿Cómo varía el tiempo de ejecución al aumentar `n`? ¿La relación es lineal?
3. ¿Qué tamaño tiene el buffer por defecto en `BufferedOutputStream`? ¿Puedes cambiarlo? (Pista: constructor con tamaño).

---

## Entregables

- Código fuente `EscrituraBuffered.java`.
- Tabla de resultados con tiempos medidos.
- Respuestas a las preguntas de análisis.
- Conclusión personal sobre el uso de buffers en Java.

---

## Recomendaciones finales

- Cierra siempre los flujos con `try-with-resources`.
- Para benchmarks más rigurosos considera **JMH**, ya que la JVM aplica optimizaciones (JIT, escape analysis) que pueden distorsionar mediciones simples.
- Ejecuta varias iteraciones y descarta la primera (calentamiento de la JVM).
- Usa valores de `n` variados para observar tendencias.