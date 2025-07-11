# space_invaders_LI


¡Hola! Este es un proyecto académico que implementa el clásico juego Space Invaders en Java.

El objetivo principal no era solo recrear el juego, sino refactorizar una versión básica para aplicar tres patrones de diseño fundamentales del "Gang of Four" (GoF), demostrando cómo mejoran la estructura, flexibilidad y mantenimiento del código.

Patrones de Diseño Implementados

El núcleo de este proyecto es la aplicación práctica de los siguientes patrones:

1. Singleton (GameManager)

¿Qué hace? Gestiona el estado global del juego: si la partida está activa, en pausa, o terminada, y qué mensajes mostrar en pantalla ("Game Over", "Click to Start", etc.).

¿Por qué se usó? Para garantizar que solo exista una única fuente de verdad sobre el estado del juego. Cualquier parte del código que necesite saber si la partida ha terminado, le pregunta al GameManager. Esto evita tener variables de estado duplicadas y posibles inconsistencias.

2. Factory (AlienFactory)

¿Qué hace? Centraliza la creación de los objetos Alien.

¿Por qué se usó? En lugar de que la clase Board cree aliens directamente con new Alien(), le pide a la AlienFactory que los construya. Esto hace que el código sea más limpio y fácil de extender. Si en el futuro quisiéramos añadir "Aliens Rápidos" o "Aliens Blindados", solo tendríamos que modificar la fábrica, sin tocar la lógica del tablero.

3. Strategy (MovementStrategy)

¿Qué hace? Define cómo se mueve el enjambre de aliens. La lógica del movimiento (ir de lado a lado y bajar) está encapsulada en su propia clase (StandardMovement).

¿Por qué se usó? Para hacer que el algoritmo de movimiento sea intercambiable. La clase Board simplemente le dice a la estrategia "muévete", sin preocuparse por los detalles de cómo lo hace. Podríamos crear fácilmente una nueva estrategia de movimiento en zigzag y cambiarla en caliente sin modificar ninguna otra clase.

Cómo Jugar

La forma más sencilla de ejecutar el proyecto es usando un IDE como IntelliJ IDEA.

Clona o descarga este repositorio en tu ordenador.

Abre el proyecto en IntelliJ IDEA (File -> Open... y selecciona la carpeta del proyecto).

Navega en el explorador de proyectos a src/spaceinvaders/core/Main.java.

Haz clic en el triángulo verde de "play" que aparece junto al método main y selecciona "Run 'Main.main()'".

¡A jugar!

Estructura del Proyecto

El código está organizado en paquetes para separar responsabilidades:

Generated code
src/
└── spaceinvaders/
    ├── core/      # El núcleo del juego (Board, Main, GameManager)
    ├── model/     # Las clases que representan los objetos (Player, Alien, etc.)
    └── patterns/  # Las implementaciones de los patrones de diseño
