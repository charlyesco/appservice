pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    environment {
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

        stage('Deploy Docker') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                echo "Desplegando versión: ${env.BUILD_NUMBER}"
                // Usar docker compose (sin guion) desde el directorio actual
                sh "docker compose -f docker-compose.yml build --no-cache app-service"
                sh "docker compose -f docker-compose.yml up -d app-service"
            }
        }
    }

    post {
        always {
            echo "Pipeline finalizado."
        }
    }
}