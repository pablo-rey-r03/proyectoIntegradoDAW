<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <h1>ÁGORA</h1>
  <h2>Proyecto Integrado | D.A.W. | 2023/24</h2>
  <p>Este proyecto consiste en la elaboración de una aplicación para
  gestionar actividades en general. Los proveedores (ofertantes o <i>strategos</i>)
  podrán ponerse en contacto con los clientes (consumidores o <i>polites</i>) y
  viceversa.
  Los ofertantes podrán registrarse como tales, consultar las
  solicitudes de los clientes y publicar actividades. Los clientes
  podrán registrarse como tales, consultar las actividades publicadas
  por los ofertantes, y publicar sus propias solicitudes.
  La aplicación se desplegará en la web, de forma que los usuarios
  sólo necesitarán un navegador y conexión a internet para poder
  acceder.</p>
  <h3>Objetivo</h3>
  <p>La razón de elaboración del proyecto viene dado por la necesidad
  del ser humano de salir de su espacio de confort e innovar en su
  tiempo libre. Esta aplicación facilitará el contacto entre
  proveedores de actividades y demandantes de formas de salir de la
  rutina en su tiempo ocioso.</p>
  <h3>Tecnologías de desarrollo</h3>
  <p>La arquitectura de nuestra aplicación será descentralizada y
  modular, pudiendo distinguir diferentes secciones:
  <ul>
    <li>En un contenedor Docker, se instalará una imagen de MariaDB
    que alojará la <b>base de datos</b>, empleada para guardar la
    información personal de los usuarios, actividades
    registradas, etc.</li>
    <li>La base de datos será utilizada mayormente por una API REST
    realizada con Spring Boot, un <i>framework</i> de desarrollo en Java.
    Este será nuestro <i><b>backend</b></i>, y nos proporcionará los métodos
    que serán llamados desde la web en sí y devolverán la
    información guardada en la base de datos.</li>
    <li>El <i><b>frontend</b></i> de la aplicación consistirá en un proyecto de
    Angular, un <i>framework</i> de desarrollo en JavaScript óptimo para
    plantear un diseño modular, y emplearemos algunas librerías
    adicionales, como Bootstrap, para dotar de un aspecto
    más atractivo a las vistas del usuario.</li>
  </ul>
  Todo el software utilizado es libre y puede instalarse de manera
  sencilla mediante comandos en el terminal o con sus respectivos
  comprimidos.</p>
</body>
</html>
