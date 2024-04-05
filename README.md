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

# DESPLIEGUE EN AWS

1. Creamos 3 instancias EC2, 1 para el proxy y 2 para el mathserver, todas en el mismo grupo de seguridad y con la misma llave
   ![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/435c449d-7a11-4563-b2b6-2b39938d1b45)
2. Ingresamos a cada instancia por medio de la consola de comandos
   ![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/61aa4879-55c7-4eda-a350-3b7093c20202)
3. en cada una de las instancias instalamos git, maven y java con el comando
   ```
   sudo yum install git
   sudo yum install java
   sudo yum install maven
   ```
4. en cada instancia clonamos el repositorio git
   ```
   git clone https://github.com/NicolasCastro9/AREP_PARCIAL2.git
   ```
5. en cada instancia ingrsamos al directorio del pom e instalamos las dependencias
   ```
   mvn clean install
   ```
   ![image](https://github.com/NicolasCastro9/AREP_PARCIAL2/assets/98556822/9f8a7f60-a83f-4ae7-bc3e-7ee66fb98550)
6. en la instancia del proxy ingresamos el siguiente comando para ejecutar el servicio proxy, remplasamos el localhost con la ip publica de las otras 2 instancias
   java -cp "target/classes;target/dependency/*" com.edu.examen.ProxyServer
   ```
   java -cp "target/classes:target/dependency/*" com.edu.examen.ProxyServer 172.31.34.166:35000 172.31.32.247:35000
   ```
7. en las otras dos instancias ingresamos el comando para ejecutar el servicio de las busquedas
   ```
   java -cp "target/classes:target/dependency/*" com.edu.examen.MathServer
   ```
8. ingresamos con el DNS publico de la maquina proxy
   ```
   http://ec2-54-167-254-55.compute-1.amazonaws.com:4567/
   ```
   
