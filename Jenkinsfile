pipeline {
    agent any

    stages {
        stage('clone') {
            steps {
                checkout scm
            }
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