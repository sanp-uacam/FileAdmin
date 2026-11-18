Alumno: Jaime Michel Garcia Sostenes

Facultad De Ingeniería 

Reporte Practica 01: Comparacion de Streams

El uso de BufferedOutputStream suele ser decenas o cientos de veces más rápido debido a los siguientes factores de arquitectura de software y hardware:

Con FileOutputStream: En cada una de las 100,000 iteraciones del bucle for, la JVM le pide directamente al sistema operativo 
que ejecute una operación física de escritura de I/O en el disco. Cada llamada implica un cambio de contexto (context switch) 
entre el espacio de usuario y el modo kernel de la CPU, lo que genera un gran consumo de recursos.

Con BufferedOutputStream: El flujo guarda temporalmente las líneas en una matriz de bytes en la memoria RAM (un buffer interno que por defecto es de 8192 bytes). La llamada al sistema operativo únicamente ocurre cuando el buffer se llena completamente o cuando se llama a flush() / close(). 
En lugar de realizar 100,000 escrituras en disco, realiza solo unas pocas decenas.

Escribir datos repetidamente en la memoria RAM es varios órdenes de magnitud más rápido que interaccionar de forma individual con la controladora del 
almacenamiento físico (disco duro o disco de estado sólido).
