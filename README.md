# Hobby Lobby

## Descripción

Este proyecto se trata de una **nueva red social organizada en comunidades por países**. A través de esta plataforma, los usuarios pueden encontrar personas con intereses comunes, ya sea por su organización, pasatiempos u otros temas de afinidad. En lugar de ofrecer un espacio de interacción directa entre usuarios, esta aplicación se centra en una **funcionalidad similar a Linktree**, presentando una lista de redes sociales del usuario para facilitar su conexión en otras plataformas.

## Tabla de Contenidos

1. [Descripción](#descripción)  
2. [Características](#características)  
3. [Tecnologías y Servicios Utilizados](#tecnologías-y-servicios-utilizados)  
4. [Instalación](#instalación)  
5. [Uso](#uso)  
6. [Creación de APIs y Almacenamiento](#creación-de-apis-y-almacenamiento)  
7. [Contribuciones](#contribuciones)  

## Características

Lista de las principales funcionalidades del proyecto:

- **Comunidades creadas por usuarios**: Espacios temáticos organizados en función de intereses y hobbies compartidos, donde los usuarios pueden unirse y conectar con personas afines.  
- **Perfiles personalizables**: Opciones para que cada usuario pueda configurar su perfil de manera que refleje sus gustos, intereses y personalidad.  
- **Listado de redes sociales y enlaces importantes**: Cada usuario tiene la posibilidad de agregar y compartir su lista de redes sociales y enlaces relevantes, facilitando el acceso a otras plataformas donde interactúa.  

## Tecnologías y Servicios Utilizados

Librerías, servicios o APIs externas que emplea el proyecto:  

- **Firebase**: Integra funcionalidades como:
  - Autenticación de usuarios.
  - Almacenamiento en tiempo real.
  - Bases de datos no relacionales.
  - Almacenamiento en la nube.
  - Herramientas de análisis y monitoreo de rendimiento.  

- **Coil**: Biblioteca para la **carga de imágenes en Android Compose**.  

## Instalación

1. **Descarga del APK** desde el repositorio o desde el enlace proporcionado.  
2. **Instalación del APK**: Sigue las instrucciones del sistema para instalar la aplicación en tu dispositivo Android.  

## Uso

Instrucciones para ejecutar el proyecto:  

1. Ejecuta la app previamente instalada.  
2. Inicia sesión o crea un usuario utilizando Google, Meta o X.  
3. Crea un perfil con **nombre, imagen, descripción y lista de redes sociales**.  
4. Busca comunidades de **gustos o hobbies**.  
5. Únete a una comunidad o explora los usuarios que pertenecen a ella.  

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
