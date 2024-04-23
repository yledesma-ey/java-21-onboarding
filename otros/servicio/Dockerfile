# Usar la imagen oficial de Node.js como base
FROM node:14

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo package.json y package-lock.json
COPY index.js ./
COPY package*.json ./

# Instalar las dependencias del proyecto
RUN npm install

# Copiar el resto de los archivos de la aplicación
COPY . .

# Exponer el puerto 3000
EXPOSE 3000

# Comando para iniciar la aplicación
CMD ["node", "index.js"]
