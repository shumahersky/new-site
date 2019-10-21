pipelineJob ("Site") {
    logRotator {
          numToKeep (10)
    }
  environmentVariables {
    registry = "shumahersky1985/labs"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
      stages {
        stage ('Clone') {
            steps {
                git 'https://github.com/shumahersky/new-site.git'
            }
        }
        stage('Building image') {
          steps{
            script {
               dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }
          }
        }
        stage('Deploy Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
              }
            }
          }
        }
            stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }

       stage('Docker Prune') {
          steps {
            sh "docker image prune -fa"
            deleteDir()
          }
       }
      }
}
