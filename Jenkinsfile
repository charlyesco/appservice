pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    environment {
        // Obtenemos la ruta de Docker usando la herramienta que configuramos
        DOCKER_HOME = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
    }

    stages {
        stage('Clone Repo') {
            steps {
                git url: 'https://github.com/charlyesco/appservice.git',
                    credentialsId: 'adf167c5-19f2-4d5a-be83-c15e7d1f7143',
                    branch: 'develop'
            }
        }

        stage('Build App') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                sh "docker build -t app-service:${env.BUILD_NUMBER} ."
            }
        }

        stage('Deploy Docker') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                echo "Desplegando versión: ${env.BUILD_NUMBER}"
                sh "docker stop workspace-app-service-1 workspace/app-service app-service || true"
                sh "docker rm workspace-app-service-1 workspace/app-service app-service || true"
                
                sh """docker run --name workspace-app-service-1 \
                    --network workspace_default \
                    --label com.docker.compose.project=workspace \
                    --label com.docker.compose.service=app-service \
                    -e DB_URL='jdbc:mysql://MyDatabase:3306/MyDatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true' \
                    -e DB_HOST='MyDatabase' \
                    -e DB_USER_NAME='root' \
                    -e DB_PASSWORD='ESCORIAL' \
                    -d -p 8080:8080 app-service:${env.BUILD_NUMBER}"""
            }
        }
    }

    post {
        always {
            echo "Pipeline finalizado."
        }
    }
}