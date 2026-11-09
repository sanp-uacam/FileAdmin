# Práctica 01: Sistema de Notas (con promedio)

## Objetivo
Completar el programa `Practica_01.java` para que funcione como un sistema de notas con menú, usando `Act_Stream` para guardar/leer notas y calculando el **promedio** de las notas registradas.

---

## Tareas

### 1. Método `agregarNota(Scanner scanner)`
Actualmente pide la nota pero **no la guarda**. Debes:
- Llamar a `Act_Stream.guardarNota(nota)` para escribirla en el archivo.
- Envolver la llamada en un `try-catch (IOException e)`.
- Mostrar `"Nota guardada."` si todo va bien, o el error si falla.

### 2. Método `verNotas()`
Tiene dos errores:
- La variable `notas` se declara dentro del `try` pero se usa fuera → **declárala antes del `try`**.
- El `catch` está vacío → **muestra el mensaje de error** con `e.getMessage()`.

### 3. Método `calcularPromedio()` ⚠️ NUEVO
Debes crear este método que:
- Lea todas las notas desde `Act_Stream.leerNotas()`.
- Recorra cada línea y convierta el texto a número con `Double.parseDouble(...)`.
- Sume los valores y divida entre el total de notas.
- Devuelva el promedio (`double`).
- Maneje `IOException` y `NumberFormatException` (por si hay líneas no numéricas).
- Si no hay notas, devuelve `0` y muestra `"No hay notas registradas."`.

### 4. Menú actualizado
Agrega las nuevas opciones:

```
--- Menú ---
1. Agregar nota
2. Ver todas las notas
3. Calcular promedio
4. Salir
```

- **Opción 3** → llama a `calcularPromedio()` y muestra: `"Promedio: " + promedio`.

---

## Funcionamiento esperado

| Entrada | Resultado |
|---------|-----------|
| `1` + "8.5" | Guarda la nota y confirma |
| `2` | Muestra todas las notas |
| `3` | Muestra `Promedio: X.XX` |
| `abc` | `"Debes ingresar un número."` |
| `9` | `"Opción inválida."` |
| `4` | Sale con `"¡Hasta luego!"` |

---

## Entregables
- `Practica_01.java` corregido y con `calcularPromedio()` implementado.
- `notas.txt` con al menos 3 notas numéricas de prueba.
- Captura de pantalla mostrando el promedio calculado.

---

## Evaluación
| Criterio | % |
|----------|---|
| `agregarNota()` guarda en archivo | 20 |
| `verNotas()` corregido | 20 |
| `calcularPromedio()` funciona correctamente | 30 |
| Manejo de excepciones | 10 |
| Programa compila y ejecuta | 10 |
| Entregar atravez de Git | 10 |