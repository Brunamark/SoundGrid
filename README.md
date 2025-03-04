# SoundGrid
SoundGrid is a versatile API designed to seamlessly integrate with multiple platforms.

## Project Structure

```plaintext
music-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── soundgrid/
│   │   │           └── api/
│   │   │               ├── ApiApplication.java
│   │   │               ├── domain/                           # Domain Layer
│   │   │               │   ├── model/                        
│   │   │               │   │   └── Music.java                # Core
│   │   │               │   ├── types/
│   │   │               │   │   └── Genre.java   
│   │   │               │   └── exception/                    
│   │   │               │          └── MusicNotFoundException.java
│   │   │               │                               
│   │   │               │
│   │   │               ├── application/                      # Application Layer
│   │   │               │   ├── port/                         
│   │   │               │   │   ├── input/                    # External communication systems with core
│   │   │               │   │   │   └── MusicUseCase.java
│   │   │               │   │   └── output/                   # Core communication with external systems
│   │   │               │   │       └── MusicRepository.java
│   │   │               │   └── service/                    
│   │   │               │       └── MusicServiceImpl.java    # Implements MusicUseCase (CRUD)
│   │   │               │
│   │   │               └── infraestructure/                   # Infrastructure Layer
│   │   │                   ├── adapter/                      
│   │   │                   │   ├── input/                    # Input Adapters
│   │   │                   │   │   └── rest/                 # REST Controllers
│   │   │                   │   │       ├── MusicController.java
│   │   │                   │   │       ├── request/          # Request DTOs
│   │   │                   │   │       │   └── MusicRequest.java
│   │   │                   │   │       └── response/         # Response DTOs
│   │   │                   │   │           └── MusicResponse.java
│   │   │                   │   │   
│   │   │                   │   └── output/                   # Output Adapters
│   │   │                   │       └── persistence/          
│   │   │                   │          ├── MusicRepositoryImpl.java #Implements Spring Data Repositories
│   │   │                   │          ├── entity/           
│   │   │                   │          │   └── MusicEntity.java
│   │   │                   │          ├── mapper/           # Entity-Domain Mappers
│   │   │                   │          │   └── MusicMapper.java
│   │   │                   │          └── repository/       # Spring Data Repositories
│   │   │                   │              └── SpringDataMusicRepository.java
│   │   │                   │                 
│   │   │                   │          
│   │   │                   └── config/                       # Configuration Classes
│   │   │                       └── BeanConfiguration.java
│   │   │                       
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── logback-spring.xml                            # Log Settings
|   |       └── schema.sql                                    # Database schema
│   └── test/                                                 # Unit and Integration tests
└── pom.xml                                                  # Maven Dependencies
└── data/
    └── app.db                                              # Database file
└── logs/
    └── api.log                                              # Log file


```

## 🔧 Application Setup 

#### Prerequisites
<ul>
	<li><strong>Java 21 (JDK 21)</strong> – You can find it on <a href="https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html">Oracle</a>.</li>
	<li><strong>Maven 3.9.9</strong> – You can find it on <a href="https://maven.apache.org/download.cgi">Maven</a>.</li>
</ul>

## 🚀 How to Run the Application

Run the command in terminal :
```plaintext
   $ mvn spring-boot:run
```
After that, this list will be displayed in the terminal:
```
🎵 MENU 🎵
1. Listar músicas
2. Buscar música por ID
3. Cadastrar nova música
4. Atualizar música
5. Deletar música
Ctrl + C. Sair
Escolha uma opção: 
```
You can choose a number between 1 and 5 to interact with the application, corresponding to creating, updating, deleting, listing all music, or finding music by ID. If you want to exit the application, you can press `Ctrl + C`. It's important to note that this application has constraints, and if an error occurs, you can trace these constraints in the file: `./logs/api.log`, as shown as below:
```
2025-03-04 11:01:27 [restartedMain] INFO  o.s.b.a.l.ConditionEvaluationReportLogger - 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-03-04 11:01:27 [restartedMain] ERROR o.s.boot.SpringApplication - Application run failed
com.soundgrid.api.domain.exception.MusicNotFoundException: Music not found with id: 3
	at com.soundgrid.api.application.service.MusicServiceImpl.lambda$0(MusicServiceImpl.java:27)
	at java.base/java.util.Optional.orElseThrow(Optional.java:403)
```
Note: This application uses JUnit test in MusicService. You can run the test using this command:
```
$ mvn test -Dtest=MusicServiceTests

```
After that you can see if these tests pass or fail in terminal as shown below:
```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.769 s -- in com.soundgrid.api.MusicServiceTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.850 s
[INFO] Finished at: 2025-03-04T12:17:51-03:00
[INFO] ------------------------------------------------------------------------
```

## ✅ Test Cases
### 1️⃣ Create Music (Cadastrar nova música)
<ul>
    <li>Given valid Music data, when calling createMusic, it should return "✅ Música cadastrada com sucesso!"</li>
    <li>Given an empty or null title, it should ask to put a valid title and show this on terminal "❌ O título não pode estar vazio.". </li>
    <li>Given an empty or null artist, it should ask to put a valid title and show this on terminal "❌ O artista não pode estar vazio.". </li>
    <li>Given an empty or null album, it should be possible to create a music without album. </li>
    <li>Given an empty, null genre or a invalid genre, it should ask to put a valid genre and show this on terminal "❌ Gênero inválido! Escolha um dos seguintes: [POP, ROCK, HIP_HOP_RAP, JAZZ, ELETRONICA, CLASSICA, BLUES, COUNTRY, REGGAE, R_B, FUNK, METAL, SOUL, FOLK, SERTANEJO, SAMBA, FORRO, BOSSA_NOVA]". </li>
    <li>Given an empty, null or invalid format date , it should ask to put a valid release date and show this on terminal "❌ Data inválida! Use o formato correto: YYYY-MM-DD.". </li>
    <li>Given an empty, null or negative duration, it should ask to put a valid duration and show this on terminal "❌ A duração deve ser um número positivo.". </li>
    <li>Given an empty or null file path, it should ask to put a valid file path and show this on terminal "❌ O caminho do arquivo não pode estar vazio.". </li>
    <li>Given an title that exceed 100 characters, it should terminate the application and show an ERROR in api.log "The title cannot be longer than 100 characters.". </li>
    <li>Given an artits that exceed 50 characters, it should terminate the application and show an ERROR in api.log "The artist name cannot be longer than 50 characters.". </li>
    <li>Given an album that exceed 100 characters, it should terminate the application and show an ERROR in api.log "The album cannot be longer than 50 characters.". </li>
    <li>Given an release date that is in the future, it should terminate the application and show an ERROR in api.log "The release date cannot be in the future.". </li>
    <li>Given an file paath that exceed 100 characters or has less than 10 characters, it should terminate the application and show an ERROR in api.log "The file path must be between 10 and 100 characters long.". </li>
</ul>

### 2️⃣ Get Music by ID (Buscar música por ID)

<ul>
	<li>Given a valid musicId, it should return the correct music object.</li>
	<li>Given an invalid musicId, it should terminate the application and show an ERROR in api.log "Music not found with id: ${id}".</li>
</ul>

### 3️⃣ Get All Music (Listar músicas)
<ul>
	<li>Type 1 in terminal it should list all musics.</li>
</ul>

### 4️⃣ Delete Music (Deletar música)
<ul> 
	<li>Given a valid musicId, it should return the correct music object.</li>
 	<li>Given an invalid musicId,it should terminate the application and show an ERROR in api.log "Music not found with id: ${id}".</li>
</ul> 

### 5️⃣ Update Music (Atualizar música)
<ul>
    <li>Given valid Music data, when calling createMusic, it should return "✅ Música atualizada com sucesso!"</li>
    <li>Given an empty or null title, it should mantain the last title value. </li>
    <li>Given an empty or null artist, it should mantain the last artist value. </li>
    <li>Given an empty or null album, it should mantain the last album value.</li>
    <li>Given an empty genre, it should mantain the last genre value. </li>
    <li>Given an empty date, it should mantain the last date value. </li>
    <li>Given an invalid format date , it should ask to put a valid release date and show this on terminal "❌ Data inválida! Use o formato correto: YYYY-MM-DD.". </li>
    <li>Given an empty duration, it should mantain the last duration value. </li>
    <li>Given an negative duration, it should ask to put a valid duration and show this on terminal "❌ A duração deve ser um número positivo.". </li>
    <li>Given an empty file path, it should mantain the last file path value. </li>
    <li>Given an title that exceed 100 characters, it should terminate the application and show an ERROR in api.log "The title cannot be longer than 100 characters.". </li>
    <li>Given an artits that exceed 50 characters, it should terminate the application and show an ERROR in api.log "The artist name cannot be longer than 50 characters.". </li>
    <li>Given an album that exceed 100 characters, it should terminate the application and show an ERROR in api.log "The album cannot be longer than 50 characters.". </li>
    <li>Given an release date that is in the future, it should terminate the application and show an ERROR in api.log "The release date cannot be in the future.". </li>
    <li>Given an file paath that exceed 100 characters or has less than 10 characters, it should terminate the application and show an ERROR in api.log "The file path must be between 10 and 100 characters long.". </li>
    <li>Given an invalid musicId, it should terminate the application and show an ERROR in api.log "Music not found with id: ${id}".</li>
</ul>

## ⚠️ Common Issues & Troubleshooting

### 🚀 Maven Build Fails
**Issue:** Running `mvn clean install` results in an error like: 

```
Could not find or load main class

```
**Solution:** Ensure you have JDK 21 installed and configured properly:
```bash
$ java -version
```
if still having issues try to set the correct JDK:

```
export JAVA_HOME=/path/to/jdk21

```

### 🐞 Dependency Not Found
**Issue:** Maven throws an error:
```
Could not resolve dependency org.springframework.boot:spring-boot-starter:3.4.3
```

**Solution:** Try updating your local dependencies:

```
mvn clean install -U
```

### 🔧 Application Port Conflict
**Issue:** Trying to start the app but getting:
```
Port 8080 is already in use

```
**Solution:** Stop the process using the port:
```
lsof -i :8080   # Mac/Linux
netstat -ano | findstr :8080  # Windows
kill -9 <PID>   # Terminate the process (replace <PID> with the actual process ID)

```
## 🏗️ Project Architecture – Hexagonal (Ports & Adapters)
This project follows the Hexagonal Architecture (Ports & Adapters) to separate business logic from external dependencies, making it scalable, maintainable, and testable.

### 📌 Layers Overview

1️⃣ Domain Layer → Business rules and core models

2️⃣ Application Layer → Use cases (service layer)

3️⃣ Infrastructure Layer → Communication with external systems

4️⃣ Adapters → Interfaces between external systems and the core

#### 1️⃣ Domain Layer (domain/) 🏛️
📍 Purpose: Contains business rules, entities, and exceptions.
✔ Independent from frameworks
✔ No external dependencies

📂 Files:
<ul>
	<li>model/Music.java → Core business entity</li>
	<li>types/Genre.java → Enum for music genres</li>
	<li>exception/MusicNotFoundException.java → Custom exception</li>
</ul>

#### 2️⃣ Application Layer (application/) 🛠️
📍 Purpose: Handles use cases and coordinates business logic.
✔ Implements Ports (Input/Output Interfaces)
✔ Calls repositories and services

📂 Files:

<ul>
	<li>port/input/MusicUseCase.java → Defines available operations</li>
	<li>port/output/MusicRepository.java → Abstracts persistence</li>
	<li>service/MusicServiceImpl.java → Implements MusicUseCase (CRUD)</li>
</ul>

#### 3️⃣ Infrastructure Layer (infrastructure/) 🏗️
📍 Purpose: Connects the application to external systems.
✔ Defines adapters for REST, persistence, and config
✔ Implements repositories, controllers, and mappers

📂 Files:

<ul>
	<li>Input Adapters (adapter/input/)</li>
	<li>rest/MusicController.java → Handles HTTP requests</li>
	<li>request/MusicRequest.java → DTO for requests</li>
 	<li>response/MusicResponse.java → DTO for responses</li>
        <li>Output Adapters (adapter/output/)</li>
	<li>persistence/MusicRepositoryImpl.java → Implements MusicRepository</li>
 	<li>entity/MusicEntity.java → Persistence entity</li>
  	<li>mapper/MusicMapper.java → Converts MusicEntity ⇄ Music</li>
   	<li>repository/SpringDataMusicRepository.java → Spring Data repository</li>
	
</ul>

#### 4️⃣ Configuration (config/) ⚙️
📍 Purpose: Holds configurations (Beans, properties, and logging).

📂 Files:
<ul>
	<li>config/BeanConfiguration.java → Defines Spring Beans</li>
	<li>resources/application.properties → App settings</li>
	<li>resources/logback-spring.xml → Logging configuration</li>
</ul>

#### 🔄 Summary – How the Layers Interact

User Request → Controller (Input Adapter) → Service Layer (Use Case) → Repository (Output Adapter) → Database





