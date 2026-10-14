# Reporte de Práctica: Comparación de BufferedOutputStream y FileOutputStream

- **Alumno:** Arturo Pérez Kantún
- **Matrícula:** 56967
- **Materia:** Administración de Archivos
- **Profesor:** Sergio A. Noh Puch
- **Actividad:** 2doParcial_P01 - BufferOutputStream

---

## 1. Tablas de Resultados y Tiempos Medidos

### Tabla 1: Escritura con `BufferedOutputStream` (Con Buffer)

| Tamaño (bytes) | Ejecución 1 (ms) | Ejecución 2 (ms) | Ejecución 3 (ms) | Promedio (ms) |
|---|---|---|---|---|
| **100,000** | 13.0 ms | 5.0 ms | 4.9 ms | **7.6 ms** |
| **1,000,000** | 31.5 ms | 33.0 ms | 28.3 ms | **30.9 ms** |
| **10,000,000** | 344.3 ms | 348.8 ms | 346.8 ms | **346.6 ms** |

### Tabla 2: Escritura con `FileOutputStream` directo (Sin Buffer)

| Tamaño (bytes) | Ejecución 1 (ms) | Ejecución 2 (ms) | Ejecución 3 (ms) | Promedio (ms) |
|---|---|---|---|---|
| **100,000** | 496.0 ms | 480.2 ms | 464.3 ms | **480.2 ms** |
| **1,000,000** | 5,302.3 ms | 6,369.4 ms | 6,007.8 ms | **5,893.2 ms** (~5.9 s) |
| **10,000,000** | 61,144.3 ms | 66,387.1 ms | 71,804.4 ms | **66,445.3 ms** (~66.4 s) |

> **Nota sobre las recomendaciones (calentamiento de la JVM):** En la primera tabla se puede ver que en la Ejecución 1 con 100,000 bytes tardó 13.0 ms y en las siguientes bajó a 5.0 ms. Esto ocurre por el calentamiento de la máquina virtual (JVM) y las optimizaciones que hace en tiempo de ejecución (JIT), tal como se señaló en las recomendaciones.

---

## 2. Preguntas de Análisis

### 1. ¿Qué diferencia observaste entre usar `BufferedOutputStream` y `FileOutputStream` directo? ¿Por qué ocurre?
La diferencia es grandísima. Usando `BufferedOutputStream` el programa termina casi de inmediato, mientras que con `FileOutputStream` directo se tarda muchísimo, sobre todo cuando son muchos datos (con 10 millones tardó más de un minuto).

Esto ocurre porque `FileOutputStream` intenta escribir cada byte directamente al disco uno por uno, y acceder al disco tantas veces hace que el proceso sea muy lento. En cambio, `BufferedOutputStream` junta los bytes en la memoria RAM (en un buffer) y cuando se llena los escribe todos juntos en un solo bloque, reduciendo bastante las escrituras al disco.

### 2. ¿Cómo varía el tiempo de ejecución al aumentar $n$? ¿La relación es lineal?
Sí, entre más bytes escribimos, más tiempo tarda. Si aumentamos 10 veces la cantidad de datos, el tiempo también aumenta aproximadamente 10 veces en ambos casos. Solo que con buffer el aumento casi no se nota porque trabaja en memoria RAM, mientras que sin buffer el tiempo se dispara muchísimo porque escribir directo a disco cuesta mucho más.

### 3. ¿Qué tamaño tiene el buffer por defecto en `BufferedOutputStream`? ¿Puedes cambiarlo?
Por defecto tiene un tamaño de **8192 bytes** (que son 8 KB).  
Sí se puede cambiar si le ponemos el tamaño que queremos dentro del constructor, por ejemplo:

```java
// Para crear un buffer de 16 KB:
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ruta), 16384);
```

---

## 3. Conclusión Personal

En esta práctica pude ver claramente la importancia de usar buffers al manejar archivos en Java. Al principio no creí que fuera tanta la diferencia, pero ver que sin buffer tardó más de un minuto en escribir los 10 millones de bytes y con buffer tardó menos de medio segundo me demostró que para escribir datos siempre es mejor usar `BufferedOutputStream`.
