pipeline {
    agent any

    environment {
        SLACK_CHANNEL = "#jenkins"
    }

    stages {
        stage('start') {
            steps {
                slackSend(channel: SLACK_CHANNEL, color: '#000000', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
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
                sh './gradlew -x integrationTests clean build'
            }
        }

        stage('unit test') {
            steps {
                junit '**/build/test-results/test/*.xml'
            }
        }

        stage('integration test') {
            steps {
                echo 'integration test ...'
            }
        }

        stage('publish') {
            steps {
                def server = Artifactory.server 'Default Artifactory Server'
                def uploadSpec = """{
                    "files": [
                        {
                            "pattern": "target/online-marketplace.jar",
                            "target": "artifacts/${BUILD_NUMBER}/",
                            "props": "Unit-Tested=Yes;Performance-Tested=No"
                        }
                    ]
                }"""
                server.upload(uploadSpec)
            }
        }

        stage('deploy') {
            steps {
                echo 'deploy ...'
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
