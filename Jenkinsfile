#!groovy
pipeline {
  stages {
    stage('Checkout') {
      node {
        checkout scm
      }
    }
  post {
    always {
      deleteDir()
    }
    success {
      mail to:"me@example.com", subject:"SUCCESS: ${currentBuild.fullDisplayName}", body: "Yay, we passed."
    }
    failure {
      mail to:"rafael.tulio@amediaweb.com.br", subject:"FAILURE: ${currentBuild.fullDisplayName}", body: "Boo, we failed."
    }
  }
}