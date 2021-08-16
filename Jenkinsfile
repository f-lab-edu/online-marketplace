pipeline {
    agent any

    stages {
        stage('clone') {
            checkout scm
        }

        stage('build') {
            steps {
                sh './gradlew -x test clean build'
            }
        }

        stage('test') {
            steps {
                echo 'test ...'
            }
        }
    }
}