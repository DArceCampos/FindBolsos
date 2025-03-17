# LogiEvents - Event Management System

## 📌 Índice
1. [Descripción / Description](#descripción--description)
2. [Tecnologías Utilizadas / Technologies Used](#tecnologías-utilizadas--technologies-used)
3. [Funcionalidades Principales / Main Features](#funcionalidades-principales--main-features)
4. [Instalación y Configuración / Installation and Setup](#instalación-y-configuración--installation-and-setup)
5. [Base de Datos / Database](#base-de-datos--database)
6. [Uso / Usage](#uso--usage)
7. [Consideraciones / Considerations](#consideraciones--considerations)
8. [Licencia / License](#licencia--license)

---

## 📌 Descripción / Description
**ES:** LogiEvents es una plataforma integral para la gestión de eventos masivos en Centroamérica. Permite a administradores crear y gestionar eventos, controlar el acceso y generar informes, mientras que los usuarios pueden explorar, reservar y confirmar asistencia a los eventos. 

**EN:** LogiEvents is a comprehensive platform for managing large-scale events in Central America. It allows administrators to create and manage events, control access, and generate reports, while users can explore, book, and confirm attendance.

---

## 📌 Tecnologías Utilizadas / Technologies Used
- **Backend:** NestJS (Node.js, TypeScript, Fastify/Express)
- **Frontend Web y Móvil / Web & Mobile Frontend:** React Native
- **Base de Datos / Database:** MongoDB (Atlas o local / local or Atlas)

---

## 📌 Instalación y Configuración / Installation and Setup
### ⚙️ Requisitos Previos / Prerequisites
- **Node.js** (v18+)
- **MongoDB** (Atlas o local / Atlas or local)
- **Expo CLI** (para React Native / for React Native)

### 🔧 Backend (NestJS)
```bash
# Clonar el repositorio / Clone the repository
git clone https://github.com/tu_usuario/logievents.git
cd logievents/core

# Instalar dependencias / Install dependencies
npm install

# Configurar variables de entorno / Configure environment variables
cp .env.example .env

# Ejecutar servidor en desarrollo / Run development server
npm run start:dev
```

### 🔧 Frontend Web & Móvil / Web & Mobile Frontend (React Native)
```bash
cd ../client

# Instalar dependencias / Install dependencies
npm install

# Ejecutar en modo desarrollo / Run in development mode
npm start
```
Para probar en Android, usa / To test on Android, use:
```bash
npm run android
```
---

## 📌 Base de Datos / Database
El sistema utiliza MongoDB. Configura la conexión en el archivo `.env` del backend:
```env
MONGO_URI=mongodb://localhost:27017/logievents
```
Para usar MongoDB Atlas, reemplaza `localhost` por la URL de tu instancia en la nube.

The system uses MongoDB. Configure the connection in the `.env` file:
```env
MONGO_URI=mongodb://localhost:27017/logievents
```
Replace `localhost` with your cloud instance URL if using MongoDB Atlas.

---

## 📌 Uso / Usage
1. **Regístrate e inicia sesión / Register and log in** as a user or administrator.
2. **Crea y gestiona eventos / Create and manage events** from the admin panel.
3. **Reserva entradas / Book tickets** as an end user.
4. **Consulta eventos y estados / View events and statuses** in real time.
5. **Revisa informes y estadísticas / Review reports and statistics** as an administrator.

---

## 📌 Consideraciones / Considerations
- La web debe estar alojada en un servicio gratuito accesible públicamente.
- The web app must be hosted on a publicly accessible free service.
- La app móvil debe ser nativa para Android.
- The mobile app must be native for Android.
- La interfaz está diseñada para ser intuitiva para usuarios sin experiencia técnica.
- The interface is designed to be intuitive for non-technical users.

---

## 📌 Licencia / License
#### Aunque este es un proyecto educativo y no para fines comerciales, hemos decidido utilizar una licencia para evitar plagio o molestias.
Este proyecto está licenciado bajo la Licencia MIT 

#### Although this is an educational project and not for business purposes, we have decided to use a license to avoid plagiarism or inconvenience. ####
This project is licensed under the MIT License.
