# Nueva Red Social por Comunidades

![Versión](https://img.shields.io/badge/versión-1.0-blue)

## Descripción

Este proyecto se trata de una **nueva red social organizada en comunidades por países**. A través de esta plataforma, los usuarios pueden encontrar personas con intereses comunes, ya sea por su organización, pasatiempos u otros temas de afinidad. En lugar de ofrecer un espacio de interacción directa entre usuarios, esta aplicación se centra en una **funcionalidad similar a Linktree**, presentando una lista de redes sociales del usuario para facilitar su conexión en otras plataformas.

## Tabla de Contenidos

1. [Descripción](#descripción)  
2. [Características](#características)  
3. [Tecnologías y Servicios Utilizados](#tecnologías-y-servicios-utilizados)  
4. [Instalación](#instalación) 
5. [Uso](#uso)  
6. [Librerías y Dependencias](#librerías-y-dependencias)  
7. [Creación de APIs y Almacenamiento](#creación-de-apis-y-almacenamiento)  
8. [Contribuciones](#contribuciones)  

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

## Instalación

1. **Descarga del APK** desde el repositorio o desde el enlace proporcionado.  
2. **Instalación del APK**: Sigue las instrucciones del sistema para instalar la aplicación en tu dispositivo Android.  

## Uso

Instrucciones para ejecutar el proyecto:  

1. Ejecuta la app previamente instalada.  
2. Inicia sesión o crea un usuario.  
3. Personaliza tu perfil agregando tu **nombre, imagen, descripción y lista de redes sociales**.  
4. Únete a comunidades de interés según tu país.
5. Comparte tu perfil para que otros usuarios puedan encontrar tus enlaces importantes. 

## Librerías y Dependencias

- **Firebase**: Para autenticación, almacenamiento de las comunidades, nombre, imagen descripcion de comunidades y todo los datos del usuario 
- **Coil**: Para la carga eficiente de imágenes en Android.  
- **DataStore**: Almacenamiento local del estado de sesión y del ID del usuario.  

## Creación de APIs y Almacenamiento

- Se implementó una base de datos en **Firebase** para almacenar la información de los usuarios, incluyendo:
  - Nombre, descripción y redes sociales.
  - Comunidades a las que pertenecen.  

- Cada comunidad tiene información detallada, como:
  - Imagen, nombre y descripción.
  - Lista de usuarios y usuarios pendientes de aceptación.  

  - **Almacenamiento local con DataStore**:
  - Una clave almacena el **ID del usuario** utilizado en Firebase.
  - Otra clave guarda el **estado de inicio de sesión**, indicando si el usuario está conectado.  

## Contribuciones

Si deseas contribuir al proyecto:  

1. Haz un **fork** del repositorio.  
2. Crea una nueva rama:  
   ```bash
   git checkout -b nueva-funcionalidad