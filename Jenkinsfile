pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test'){
          steps{
            sh 'mvn test -DsuiteXmlFile=src/test/resources/testng.xml'
          }
          post {
          always {
              step([$class: 'Publisher', reportFilenamePattern: 'target/surefire-reports/testng-results.xml'])
          }
}
        }
    }
}
