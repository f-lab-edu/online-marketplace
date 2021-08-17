pipeline {
    agent any

    environment {
        SLACK_CHANNEL = "#jenkins"
    }

    stages {
        stage('start') {
            steps {
                slackSend(channel: SLACK_CHANNEL, color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            }
        }

        stage('clone') {
            steps {
                checkout scm
            }
        }

        stage('build') {
            steps {
                echo 'build ...'
                sh './gradlew -x test clean build'
            }
        }

        stage('unit test') {
            junit '**/build/test-results/test/TEST-com.coupang.marketplace.*.service.*.xml'
        }

        stage('integration test') {
            steps {
                echo 'integration test ...'
            }
        }
    }

    post {
        success {
            slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "✅ SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' ${env.BUILD_URL}")
        }
        failure {
            slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "❗ FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' ${env.BUILD_URL}")
        }
    }
}
