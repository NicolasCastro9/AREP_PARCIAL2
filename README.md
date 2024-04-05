# AREP_PARCIAL2
Este proyecto consiste en un servidor de proxy que gestiona las solicitudes de búsqueda lineal y binaria, y las reenvía a otro servidor especificado. Ademas del desplegado en tres máquinas virtuales de EC2 de AWS, para provar su funcionamiento

# CLASES
1. HttpConnectionExample: Esta clase se encarga de establecer conexiones HTTP a servidores remotos utilizando la tecnica de round-robin.
2. MathServer: Configura un servidor web que proporciona endpoints para realizar búsquedas lineales y binarias en arreglos de números enteros.
3. ProxyServer: Configura un servidor web que actúa como intermediario para las solicitudes de búsqueda.

# PRE-REQUISITOS
Java: Es un lenguaje de programación de propósito general orientado a objetos, portátil y muy versátil.

Maven: Es una herramienta que maneja el ciclo de vida del programa.

Git: Es un sistema de control de versiones distribuido (VCS).

# ARQUITECTURA Y DISEÑO
El proyecto sigue una arquitectura basada en microservicios, donde cada componente se implementa como un microservicio independiente. La comunicación entre los microservicios se realiza a través de solicitudes HTTP. El servidor de proxy (ProxyServer) actúa como un punto de entrada único para las solicitudes de búsqueda, y dirige cada solicitud al microservicio apropiado (MathServer). Esto proporciona flexibilidad, escalabilidad y modularidad al sistema.

En AWS la aplicación esta desplegada en dos instancias de EC2 donde por medio del algoritmo round-robin las solicitudes son recibidas y delegadas a cada una de las instancias creadas.

![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/f3622437-f2c2-4dd9-8a9c-9f2364ab7cb9)

# INSTRUCCIONES DE USO EN LOCAL

1. Clonamos el repositorio
   ```
   git clone https://github.com/NicolasCastro9/AREP_PARCIAL2.git
   ```
2.  nos dirigmos a la capeta del pom
   ```
   cd AREP_PARCIAL2/
   ```
3. Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias definidas en el archivo
   ```
   mvn clean install
   ```
4. Corremos los servicios con los siguientes comandos en diferentes terminales
   ```
   java -cp "target/classes;target/dependency/*" com.edu.examen.ProxyServer localhost:35000
   java -cp "target/classes;target/dependency/*" com.edu.examen.MathServer
   ```
5. En el navegador ingresamos la siguiente URL
   ```
   http://localhost:4567/
   ```
   ![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/1efd4a62-f985-4ca3-9ab7-bb25769d6904)

6. Probamos las funciones de busqueda
   ![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/c8657bde-1370-4d8a-950e-57b12a3efc68)


   
