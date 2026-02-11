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
                    credentialsId: 'charlyesco',
                    branch: 'develop'
            }
        }

        stage('Build App') {
            steps {
                // El plugin de Maven ya pone 'mvn' en el PATH automáticamente al usar 'tools'
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker') {
            environment {
                // Añadimos Docker al PATH para esta etapa y la siguiente
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                sh "docker build -t springboot-deploy:${env.BUILD_NUMBER} ."
            }
        }

        stage('Deploy Docker') {
            environment {
                PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"
            }
            steps {
                echo "Desplegando versión: ${env.BUILD_NUMBER}"
                // Limpieza de contenedores antiguos
                sh "docker stop workspace-app-service-1 workspace/app-service app-service || true"
                sh "docker rm workspace-app-service-1 workspace/app-service app-service || true"
                
                // Lanzamiento con red, etiquetas de ordenación y variables de entorno
                sh """docker run --name workspace-app-service-1 \
                    --network workspace_default \
                    --label com.docker.compose.project=workspace \
                    --label com.docker.compose.service=app-service \
                    -e DB_URL='jdbc:mysql://MyDatabase:3306/MyDatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true' \
                    -e DB_HOST='MyDatabase' \
                    -e DB_USER_NAME='root' \
                    -e DB_PASSWORD='ESCORIAL' \
                    -d -p 8080:8080 springboot-deploy:${env.BUILD_NUMBER}"""
            }
        }
    }

    post {
        always {
            script {
                // notifyBuild(currentBuild.result)
            }
        }
    }
}

def notifyBuild(String buildStatus = 'STARTED'){
// build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()
  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""
}