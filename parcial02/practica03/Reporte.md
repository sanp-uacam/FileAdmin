# Práctica 03: Terminal interactiva con Log de Historial

**Materia:** Administración de Archivos  
**Alumno:** Arturo Pérez Kantún  
**Matrícula:** 56967  
**Parcial:** 2do Parcial  

---

## 1. Descripción de la Actividad

Se implementó la terminal de comandos interactiva en Java para que registre automáticamente en un archivo de log (`historial.log` / `historial_terminal.log`) cada comando ingresado (`INPUT`) y la respuesta obtenida (`OUTPUT`), guardando la fecha y hora exacta en formato `[yyyy-MM-dd HH:mm:ss]`.

### Comandos disponibles:
- `sumar [num1] [num2]`: Realiza la suma de los dos números ingresados.
- `invertir [texto]`: Invierte la cadena de texto proporcionada.
- `mayusculas [texto]`: Convierte el texto a mayúsculas.
- `help` / `ayuda`: Muestra la lista de comandos disponibles.
- `salir` / `exit()`: Cierra la terminal y finaliza el programa.

---

## 2. Pregunta de Análisis

### *¿Por qué se usa `true` en el constructor de `FileWriter`?*

**Respuesta:**  
Se utiliza `true` en `new FileWriter(archivo, true)` para habilitar el modo **append** (anexar). 
Esto le dice a Java que cada vez que se escriba en el archivo, el nuevo texto se debe colocar al final de lo que ya existe sin borrar los datos previos. Si no se pusiera `true` (o si se dejara por defecto `false`), cada vez que se abra el archivo o se guarde un nuevo comando se sobrescribiría desde el inicio, borrando todo el historial anterior.

---

## 3. Evidencia de Ejecución (`historial.log`)

A continuación se muestra el contenido real generado tras probar la terminal en consola ejecutando pruebas de validación, los comandos de suma, inversión de texto, conversión a mayúsculas, ayuda y salida:

```text
[2026-11-24 17:22:32] INPUT: 1
[2026-11-24 17:22:32] OUTPUT: Error: Comando desconocido. Intenta con 'sumar', 'invertir', 'mayusculas' o 'help'.
[2026-11-24 17:22:36] INPUT: sumar
[2026-11-24 17:22:36] OUTPUT: Error: Uso correcto -> sumar [num1] [num2]
[2026-11-24 17:22:42] INPUT: sumar 1 3
[2026-11-24 17:22:42] OUTPUT: Resultado de la suma: 4
[2026-11-24 17:22:49] INPUT: invertir arturo
[2026-11-24 17:22:49] OUTPUT: Texto invertido: orutra
[2026-11-24 17:23:02] INPUT: mayusculas arturopk
[2026-11-24 17:23:02] OUTPUT: Texto en mayusculas: ARTUROPK
[2026-11-24 17:23:07] INPUT: help
[2026-11-24 17:23:07] OUTPUT: Comandos disponibles:
 - sumar [num1] [num2]
 - invertir [texto]
 - mayusculas [texto]
 - salir / exit()
[2026-11-24 17:23:16] INPUT: salir
[2026-11-24 17:23:16] OUTPUT: Cerrando terminal... Hasta luego!
```
