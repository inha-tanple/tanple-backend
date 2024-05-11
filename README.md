<div align="center">
  <br>
<p align="center" width="100%">
    <img src="docs/images/logo.png" alt="tanple icon" style="width: 140px; height:140px; display: block; margin: auto; border-radius: 80%;">
</p>
  <h2>탄소저감 크레딧 플랫폼, 탄플 - 백엔드</h2></hr>
  <p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java badge">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white" alt="Spring badge">
    <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=Spring&logoColor=white" alt="Spring Data JPA badge">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white" alt="MySQL badge">
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white" alt="Thymeleaf badge">
</div>
</div>

## 서비스 아키텍처

<img src="docs/images/architecture.png" alt="Tanple architecture">

## ERD

<img src="docs/images/ERD.png" alt="Tanple ERD">

## pre-requisite

1. install jdk-17

```bash
## install jdk-17
```

2. clone repository

```bash
git clone https://github.com/inha-tanple/tanple-backend.git
cd tanple-backend
```

3. Install Envp using java file (e.g. yml, ...)

```bash
java InstallEnv
```

## To contributor: how to check data in h2-database

1. Run Spring Application

```
e.g. localhost:8080
```

2. then you can access h2-database in web browser in `/h2-console`

<img src="docs/images/h2-setup.png" alt="h2-setup" style="width: 40%; display: block; margin: auto;">
