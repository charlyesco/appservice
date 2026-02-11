node {
    def WORKSPACE = "/var/lib/jenkins/workspace/springboot-deploy"
    def dockerHome = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
    println "DEBUG: dockerHome is '${dockerHome}'"
    def dockerImageTag = "springboot-deploy${env.BUILD_NUMBER}"
    try{
//          notifyBuild('STARTED')
         stage('Debug Info') {
             sh "echo PATH is: \$PATH"
             sh "ls -la /var/jenkins_home/tools || true"
             sh "ls -la ${dockerHome ?: '/var/jenkins_home/tools/docker'} || true"
         }
         stage('Clone Repo') {
            // for display purposes
            // Get some code from a GitHub repository
            git url: 'https://github.com/charlyesco/appservice.git',
                credentialsId: 'charlyesco',
                branch: 'develop'
         }
          stage('Build docker') {
              withEnv(["PATH+DOCKER=${dockerHome}/bin"]) {
                 sh "docker build -t springboot-deploy:${env.BUILD_NUMBER} ."
              }
          }
          stage('Deploy docker'){
                  echo "Docker Image Tag Name: ${dockerImageTag}"
                  sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
                  sh "docker run --name springboot-deploy -d -p 8081:8081 springboot-deploy:${env.BUILD_NUMBER}"
          }
    }catch(e){
//         currentBuild.result = "FAILED"
        throw e
    }finally{
//         notifyBuild(currentBuild.result)
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