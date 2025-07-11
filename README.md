# space_invaders_LI

¡Hola! Este es un proyecto académico que implementa el clásico juego Space Invaders en Java.

Universidad Comunera
2do año - Análisis de sistemas

Lucas Irala

## Patrones de Diseño Implementados

El núcleo de este proyecto es la aplicación práctica de los siguientes patrones:

### 1. Singleton (GameManager)

#### ¿Qué hace? 
Gestiona el estado global del juego: si la partida está activa, en pausa, o terminada, y qué mensajes mostrar en pantalla ("Game Over", "Click to Start", etc.).

#### ¿Por qué se usó? 
Para garantizar que solo exista una única fuente de verdad sobre el estado del juego. Cualquier parte del código que necesite saber si la partida ha terminado, le pregunta al GameManager. Esto evita tener variables de estado duplicadas y posibles inconsistencias.

### 2. Factory (AlienFactory)

#### ¿Qué hace? 
Centraliza la creación de los objetos Alien.

#### ¿Por qué se usó? 
En lugar de que la clase Board cree aliens directamente con new Alien(), le pide a la AlienFactory que los construya. Esto hace que el código sea más limpio y fácil de extender. Si en el futuro quisiéramos añadir "Aliens Rápidos" o "Aliens Blindados", solo tendríamos que modificar la fábrica, sin tocar la lógica del tablero.

### 3. Strategy (MovementStrategy)

#### ¿Qué hace? 
Define cómo se mueve el enjambre de aliens. La lógica del movimiento (ir de lado a lado y bajar) está encapsulada en su propia clase (StandardMovement).

#### ¿Por qué se usó? 
Para hacer que el algoritmo de movimiento sea intercambiable. La clase Board simplemente le dice a la estrategia "muévete", sin preocuparse por los detalles de cómo lo hace. Podríamos crear fácilmente una nueva estrategia de movimiento en zigzag y cambiarla en caliente sin modificar ninguna otra clase.

## Cómo Jugar

#### La forma más sencilla de ejecutar el proyecto es usando un IDE como IntelliJ IDEA.

1. Clona o descarga este repositorio en tu computadora.

2. Abre el proyecto en IntelliJ IDEA *(File -> Open... y selecciona la carpeta del proyecto)*.

3. Navega en el explorador de proyectos a src/spaceinvaders/core/Main.java.

4. Haz clic en el triángulo verde de "play" que aparece junto al método main y selecciona "Run 'Main.main()'".

5. Finalmente de click al centro de la pestaña del juego.

**¡A jugar!**
