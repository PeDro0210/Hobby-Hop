# Nueva Red Social por Comunidades

![Licencia](https://img.shields.io/badge/licencia-MIT-green)  
![Versión](https://img.shields.io/badge/versión-1.0-blue)

## Descripción

Este proyecto es una **nueva red social organizada por comunidades según países**. A través de esta plataforma, los usuarios pueden encontrar personas con intereses comunes, ya sea por su organización, pasatiempos u otros temas afines. En lugar de centrarse en la interacción directa, la aplicación presenta una **funcionalidad similar a Linktree**, donde cada usuario comparte una lista de sus redes sociales para facilitar conexiones en otras plataformas.

## Tabla de Contenidos

1. [Descripción](#descripción)  
2. [Características](#características)  
3. [Tecnologías y Servicios Utilizados](#tecnologías-y-servicios-utilizados)  
4. [Uso](#uso)  
5. [Librerías y Dependencias](#librerías-y-dependencias)  

## Características

- **Comunidades creadas por usuarios**: Espacios temáticos basados en intereses y hobbies compartidos, donde los usuarios pueden unirse y conectar con personas afines.  
- **Perfiles personalizables**: Cada usuario puede configurar su perfil reflejando sus gustos, intereses y personalidad.  
- **Listado de redes sociales y enlaces importantes**: La plataforma facilita la creación de un espacio tipo Linktree donde cada usuario comparte sus redes sociales y enlaces relevantes, permitiendo una conexión fluida en otras plataformas.  

## Tecnologías y Servicios Utilizados

- **Firebase**: Provee funcionalidades esenciales como:
  - Autenticación de usuarios.
  - Bases de datos en tiempo real con Firestore y Realtime Database.
  - Almacenamiento en la nube.
  - Herramientas de análisis y monitoreo del rendimiento.  

- **Coil**: Biblioteca para **carga de imágenes** optimizada en Android Compose, facilitando la presentación fluida de avatares y contenidos visuales.

- **DataStore**: Almacenamiento local para:
  - Guardar el **ID del usuario** desde Firebase para seguimiento.
  - Registrar el **estado de inicio de sesión** del usuario, indicando si está conectado.  

## Uso

- Regístrate o inicia sesión en la aplicación.  
- Personaliza tu perfil agregando tu descripción y redes sociales.  
- Únete a comunidades de interés según tu país.  
- Comparte tu perfil para que otros usuarios puedan encontrar tus enlaces importantes.  

## Librerías y Dependencias

- **Firebase**: Para autenticación, almacenamiento de las comunidades, nombre, imagen descripcion de comunidades y todo los datos del usuario 
- **Coil**: Para la carga eficiente de imágenes en Android.  
- **DataStore**: Almacenamiento local del estado de sesión y del ID del usuario.  
