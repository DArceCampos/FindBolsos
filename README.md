# FindBolsos - Android App

## Índice
1. [Descripción / Description](#descripción--description)
2. [Tecnologías Utilizadas / Technologies Used](#tecnologías-utilizadas--technologies-used)
3. [Funcionalidades Principales / Main Features](#funcionalidades-principales--main-features)
4. [Instalación y Configuración / Installation and Setup](#instalación-y-configuración--installation-and-setup)
5. [Uso / Usage](#uso--usage)
6. [Consideraciones / Considerations](#consideraciones--considerations)
7. [Licencia / License](#licencia--license)

---

## Descripción / Description
**ES:** FindBolsos es una aplicación para Android desarrollada en Android Studio que permite a los usuarios identificar bolsos, bultos o loncheras a través del reconocimiento de imágenes y localizar tiendas de bolsos cercanas mediante Google Maps y Waze.

**EN:** FindBolsos is an Android application developed in Android Studio that allows users to identify handbags, backpacks, or lunch bags through image recognition and locate nearby bag stores using Google Maps and Waze.

---

## Tecnologías Utilizadas / Technologies Used
- **Lenguaje de Programación / Programming Language:** Java
- **Interfaz de Usuario / User Interface:** XML
- **Entorno de Desarrollo / Development Environment:** Android Studio
- **APIs Utilizadas / APIs Used:**
  - Google Maps API
  - Waze API
  - Biblioteca de Reconocimiento de Imágenes

---

## Funcionalidades Principales / Main Features
- **Reconocimiento de Bolsos:** Identifica si un objeto en la imagen es un bolso, bulto o lonchera.
- **Ubicación de Tiendas:** Muestra tiendas de bolsos cercanas en Google Maps.
- **Direcciones en Waze:** Permite abrir direcciones de tiendas en Waze para una navegación fácil.

---

## Instalación y Configuración / Installation and Setup
### Requisitos Previos / Prerequisites
- **Android Studio** (versión recomendada: 2022+)
- **Dispositivo o Emulador con Android 8.0+**
- **Conexión a Internet para APIs**

### Instalación / Installation
```bash
# Clonar el repositorio / Clone the repository
git clone https://github.com/tu_usuario/findbolsos.git
cd findbolsos

# Abrir el proyecto en Android Studio / Open project in Android Studio
```

### Configuración de APIs / API Setup
Para utilizar las funcionalidades de Google Maps y Waze, agrega las claves de API en `AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="TU_API_KEY_AQUI" />
```

Para Waze, usa:
```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <data android:scheme="waze" />
</intent-filter>
```

---

## Uso / Usage
1. **Captura o sube una imagen** para identificar si el objeto es un bolso, bulto o lonchera.
2. **Explora tiendas cercanas** en Google Maps.
3. **Navega hacia las tiendas** usando Waze.

---

## Consideraciones / Considerations
- La aplicación requiere permisos de cámara y ubicación.
- Es necesario contar con conexión a Internet para el uso de Google Maps y Waze.
- La precisión del reconocimiento de imágenes puede variar según las condiciones de la foto.

---

## Licencia / License
Este proyecto está licenciado bajo la Licencia MIT.
This project is licensed under the MIT License.

