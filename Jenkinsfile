pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    environment {
        DOCKER_HOME = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        DOCKER_COMPOSE_VERSION = '2.23.0'
    }

    stages {
        stage('Clone Repo') {
            steps {
                dir('app-service') {
                    git url: 'https://github.com/charlyesco/appservice.git',
                        credentialsId: 'adf167c5-19f2-4d5a-be83-c15e7d1f7143',
                        branch: 'develop'
                }
            }
        }

        stage('Build App') {
            steps {
                dir('app-service') {
                    sh "mvn clean package -DskipTests"
                }
            }
        }

        stage('Build Docker Image') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                sh "docker build -t app-service:latest app-service/"
            }
        }

        stage('Setup Docker Compose') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                sh '''
                    if [ ! -f /tmp/docker-compose ]; then
                        curl -L "https://github.com/docker/compose/releases/download/v${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" -o /tmp/docker-compose
                        chmod +x /tmp/docker-compose
                    fi
                    /tmp/docker-compose --version
                '''
            }
        }

        stage('Deploy with Compose') {
            environment {
                PATH = "/tmp:${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                echo "Desplegando versión: ${env.BUILD_NUMBER}"
                
                sh """
                    cd app-service
                    /tmp/docker-compose -p app-service down 2>/dev/null || true
                    docker network rm app-service_app-network 2>/dev/null || true
                    docker network rm app-service_mongo-network 2>/dev/null || true
                    
                    # Liberar puertos
                    docker stop \$(docker ps -q -f name=MyDatabase) 2>/dev/null || true
                    docker rm -f \$(docker ps -aq -f name=MyDatabase) 2>/dev/null || true
                    
                    /tmp/docker-compose -p app-service up -d
                """
            }
        }
    }

    post {
        always {
            echo "Pipeline finalizado."
        }
    }
}