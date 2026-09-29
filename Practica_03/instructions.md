# Práctica: Manipulación de Base de Datos de Alumnos con Serialización

## Objetivo

Aplicar los conceptos de **serialización**, **colecciones** y **manejo de archivos** en Java, implementando funcionalidades que operen sobre una base de datos de alumnos almacenada en un archivo binario.

---

## Código base proporcionado

### Clase `Alumno` (debe implementar `Serializable`)

```java
import java.io.Serializable;

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    private String matricula;
    private String nombre;
    private double promedio;

    public Alumno() {}

    public Alumno(String matricula, String nombre, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }

    @Override
    public String toString() {
        return "Alumno{matricula='" + matricula + "', nombre='" + nombre + "', promedio=" + promedio + "}";
    }
}
```

> ⚠️ **Nota importante:** Para que `ObjectOutputStream.writeObject(alumnos)` funcione, la clase `Alumno` **debe implementar `Serializable`**.

### Método `escribirAlumnos()`

```java
import java.io.*;
import java.util.ArrayList;

public static void escribirAlumnos() {
    var alumnos = new ArrayList<Alumno>();
    alumnos.add(new Alumno("5799", "Sergio", 9.7));
    alumnos.add(new Alumno("3466", "Diego", 9));
    alumnos.add(new Alumno("2388", "Fernando", 8.5));
    alumnos.add(new Alumno("2388", "Jaime", 10));

    try (var fos = new FileOutputStream("AlumnosDB.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(alumnos);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

---

## Funcionalidades a implementar

### 🔹 Funcionalidad 1: Leer y mostrar todos los alumnos

**Método:** `public static void leerAlumnos()`

**Descripción:** Leer el archivo `AlumnosDB.txt` y mostrar por consola todos los alumnos almacenados.

**Instrucciones:**

1. Usar `FileInputStream` junto con `ObjectInputStream`.
2. Leer el objeto con `readObject()` y hacer un **cast** a `ArrayList<Alumno>`.
3. Recorrer la lista con un `for-each` e imprimir cada alumno.
4. Manejar las excepciones `IOException` y `ClassNotFoundException`.

**Salida esperada:**

```
=== LISTA DE ALUMNOS ===
{matricula='5799', nombre='Sergio', promedio=9.7}
{matricula='3466', nombre='Diego', promedio=9.0}
{matricula='2388', nombre='Fernando', promedio=8.5}
{matricula='2388', nombre='Jaime', promedio=10.0}
```

**Esqueleto:**

```java
public static void leerAlumnos() {
    var alumnos = new ArrayList<Alumno>();
    try (var fis = new FileInputStream("AlumnosDB.txt");
         var ois = new ObjectInputStream(fis)) {
        // Tu código aquí
    } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}
```

---

### 🔹 Funcionalidad 2: Buscar un alumno por matrícula

**Método:** `public static Alumno buscarAlumnoPorMatricula(String matricula)`

**Descripción:** Leer el archivo y retornar el **primer** alumno que coincida con la matrícula proporcionada. Si no existe, retornar `null`.

**Instrucciones:**

1. Leer la lista del archivo (igual que la Funcionalidad 1).
2. Recorrer la lista comparando la matrícula con `.equals()`.
3. Retornar el alumno encontrado o `null`.

**Ejemplo de uso:**

```java
Alumno encontrado = buscarAlumnoPorMatricula("3466");
System.out.println(encontrado != null ? encontrado : "Alumno no encontrado");
```

**Salida esperada:**

```
{matricula='3466', nombre='Diego', promedio=9.0}
```

**Reto adicional:** ¿Qué pasa con la matrícula `"2388"`? ¿Cuál de los dos alumnos retorna? Explica por qué.

---

### 🔹 Funcionalidad 3: Agregar un nuevo alumno y guardar

**Método:** `public static void agregarAlumno(Alumno nuevoAlumno)`

**Descripción:** Leer la lista actual, agregar el nuevo alumno y **sobrescribir** el archivo con la lista actualizada.

**Instrucciones:**

1. Leer la lista desde el archivo (reutiliza la lógica de lectura).
2. Agregar el nuevo alumno con `alumnos.add(nuevoAlumno)`.
3. Escribir la lista actualizada con `ObjectOutputStream` (sobrescribiendo el archivo).
4. Mostrar un mensaje de confirmación.

**Ejemplo de uso:**

```java
agregarAlumno(new Alumno("1122", "Ana", 9.3));
leerAlumnos();
```

**Salida esperada:**

```
✅ Alumno agregado correctamente.
=== LISTA DE ALUMNOS ===
{matricula='5799', nombre='Sergio', promedio=9.7}
{matricula='3466', nombre='Diego', promedio=9.0}
{matricula='2388', nombre='Fernando', promedio=8.5}
{matricula='2388', nombre='Jaime', promedio=10.0}
{matricula='1122', nombre='Ana', promedio=9.3}
```

---

## Retos adicionales (opcionales)

Si terminaste las 3 funcionalidades, intenta implementar estas mejoras:

| #   | Reto                          | Descripción                                                                                                          |
| --- | ----------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| 1   | **Eliminar alumno**           | `public static boolean eliminarAlumno(String matricula)` — elimina el alumno con esa matrícula y guarda los cambios. |
| 2   | **Calcular promedio grupal**  | `public static double promedioGrupal()` — retorna el promedio de todos los alumnos.                                  |
| 3   | **Alumno con mejor promedio** | `public static Alumno mejorPromedio()` — retorna el alumno con la calificación más alta.                             |
| 4   | **Validaciones**              | Al agregar un alumno, validar que el promedio esté entre 0 y 10 y que la matrícula no esté repetida.                 |
| 5   | **Menú interactivo**          | Crear un menú con `Scanner` que permita al usuario elegir qué operación realizar.                                    |

---

## Estructura sugerida del proyecto

```
📁 PracticaAlumnos/
├── 📄 Alumno.java          → Clase serializable
├── 📄 AlumnosDB.txt        → Archivo binario (se genera al ejecutar)
├── 📄 GestionAlumnos.txt  → Contiene los métodos
└── 📄 Main.java            → Ejecución de cada metodo
```

## En `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        // 1. Crear la base de datos inicial
        escribirAlumnos();

        // 2. Leer y mostrar todos
        leerAlumnos();

        // 3. Buscar por matrícula
        System.out.println("\nBuscando matrícula 3466...");
        System.out.println(buscarAlumnoPorMatricula("3466"));

        // 4. Agregar un alumno nuevo
        agregarAlumno(new Alumno("1122", "Ana", 9.3));

        // 5. Verificar que se guardó
        leerAlumnos();
    }

}
```

---

## Criterios de evaluación sugeridos

| Criterio                                                              | Puntos |
| --------------------------------------------------------------------- | ------ |
| La clase `Alumno` implementa `Serializable` correctamente             | 1      |
| Funcionalidad 1: lectura correcta del archivo con `ObjectInputStream` | 2      |
| Funcionalidad 2: búsqueda correcta con manejo de `null`               | 2      |
| Funcionalidad 3: agregar y sobrescribir el archivo correctamente      | 2      |
| Uso correcto de **try-with-resources**                                | 1      |
| Manejo adecuado de excepciones                                        | 1      |
| Código limpio y comentado                                             | 1      |
| **Total**                                                             | **10** |

¿Quieres que te proporcione las **soluciones completas** de las 3 funcionalidades para comparar, o prefieres intentarlo primero?
