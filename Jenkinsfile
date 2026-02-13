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
                
                // Crear red si no existe
                sh "docker network create workspace_app-network 2>/dev/null || true"
                
                // Detener y eliminar contenedores anteriores
                sh "docker stop MyDatabase 2>/dev/null || true"
                sh "docker rm MyDatabase 2>/dev/null || true"
                sh "docker stop workspace-app-service-1 2>/dev/null || true"
                sh "docker rm workspace-app-service-1 2>/dev/null || true"
                
                // Iniciar MySQL
                sh "docker run -d --name MyDatabase --network workspace_app-network -e MYSQL_ROOT_PASSWORD=ESCORIAL -e MYSQL_DATABASE=MyDatabase -e MYSQL_PASSWORD=ESCORIAL -p 3307:3306 mysql:8.0.21"
                
                // Esperar a que MySQL esté listo (healthcheck)
                sh '''
                    echo "Esperando a que MySQL esté listo..."
                    for i in $(seq 1 30); do
                        if docker exec MyDatabase mysqladmin ping -h localhost --silent 2>/dev/null; then
                            echo "MySQL está listo"
                            exit 0
                        fi
                        echo "Intento $i/30..."
                        sleep 2
                    done
                    echo "MySQL no estuvo listo a tiempo"
                    exit 1
                '''
                
                // Iniciar app-service
                sh """docker run --name workspace-app-service-1 \
                    --network workspace_app-network \
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