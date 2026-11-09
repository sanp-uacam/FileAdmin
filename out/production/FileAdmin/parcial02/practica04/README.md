# Indicaciones: Agregar Log de Historial a la Terminal

## Objetivo

Modificar la terminal de consola existente para que **guarde en un archivo `historial.log`** cada comando ingresado (INPUT) y su respuesta (OUTPUT), con el formato indicado.

## Formato exacto del archivo

```
[2026-10-26 08:37:31] INPUT: sumar 3 2.5
[2026-10-26 08:37:31] OUTPUT: Resultado de la suma: 5.5
[2026-10-26 08:37:41] INPUT: invertir sergio
[2026-10-26 08:37:41] OUTPUT: Texto invertido: oigres
[2026-10-26 08:37:49] INPUT: salir
[2026-10-26 08:37:49] OUTPUT: Cerrando terminal... ¡Hasta luego!
```

- Cada línea empieza con `[yyyy-MM-dd HH:mm:ss]` (fecha y hora actual).
- Luego `INPUT: ` seguido del comando tal como lo escribió el usuario.
- Luego `OUTPUT: ` seguido del texto que la terminal muestra como respuesta.
- **Un INPUT y un OUTPUT por cada comando**, en dos líneas consecutivas.

## Pasos a seguir

1. **Crear el archivo en modo append.**
    - Usar `FileWriter("historial.log", true)` o equivalente.
    - El archivo **no debe borrarse** entre ejecuciones: se va acumulando.

2. **Registrar el INPUT antes de procesar.**
    - Justo después de leer la línea del usuario, escribir:
      ```
      [fechaHora] INPUT: <línea tal cual>
      ```

3. **Registrar el OUTPUT después de procesar.**
    - Cuando el comando produzca su respuesta, escribir:
      ```
      [fechaHora] OUTPUT: <texto de respuesta>
      ```

4. **Cubrir todos los comandos existentes** (sumar, invertir, etc.) y también el comando `salir`, que debe registrar su mensaje de despedida.

5. **Cerrar el archivo correctamente** al terminar el programa (`try-with-resources` o `close()` en el `finally`).

## Ejemplo mínimo de cómo encararlo

Dentro del bucle principal de la terminal:

```java
String entrada = scanner.nextLine();

// 1) registrar INPUT
log.escribir("INPUT: " + entrada);

String salida;
if (entrada.equalsIgnoreCase("salir")) {
    salida = "Cerrando terminal... ¡Hasta luego!";
} else {
    salida = procesar(entrada);   // tu lógica existente
}

// 2) registrar OUTPUT
log.escribir("OUTPUT: " + salida);

// 3) mostrar al usuario (como ya hacías)
System.out.println(salida);
```

Y la clase que escribe el archivo se encarga solo de anteponer el `[fechaHora]`:

```java
public void escribir(String texto) throws IOException {
    String marca = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    bw.write("[" + marca + "] " + texto);
    bw.newLine();
    bw.flush();   // importante: que se vea aunque el programa siga
}
```

## Requisitos mínimos a verificar

| ✔ | Requisito |
|---|---|
| ☐ | Se crea el archivo `historial.log` automáticamente la primera vez |
| ☐ | Al ejecutar varias veces, el archivo **crece** (no se sobrescribe) |
| ☐ | Cada comando genera **exactamente** una línea `INPUT:` y una `OUTPUT:` |
| ☐ | El formato de fecha es `[yyyy-MM-dd HH:mm:ss]` (con corchetes y espacio) |
| ☐ | El comando `salir` también queda registrado |
| ☐ | No hay excepciones sin manejar al escribir el archivo |

## Pista rápida

- Para la fecha: `LocalDateTime.now()` + `DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")`.
- Para no perder lo escrito si el programa se cierra de golpe: llama `flush()` después de cada línea.
- Si el archivo no existe, `FileWriter(..., true)` lo crea solo — no necesitas comprobarlo.

## Entrega

- El código de la terminal modificado.
- Una captura del `historial.log` resultante tras ejecutar al menos **3 comandos distintos + salir**.
- Una frase explicando: *¿por qué se usa `true` en el constructor de `FileWriter`?*

---

¿Quieres que te prepare también un **checklist imprimible** para que ellos mismos validen su entrega antes de subirla, o un **ejemplo de salida esperada** con 3 comandos para que comparen?