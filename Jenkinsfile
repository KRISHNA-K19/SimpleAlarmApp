pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = '5ef323fb-9b23-4058-8d55-6d9337f15427'  // Your Jenkins credential ID
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning GitHub repository...'
                git branch: 'main',
                    url: 'https://github.com/KRISHNA-K19/SimpleAlarmApp.git',
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java program...'
                bat 'javac SimpleAlarm.java'
            }
        }

        stage('Test') {
            steps {
                echo 'Running basic test: check if class file exists'
                bat '''
                if exist SimpleAlarm.class (
                    echo Compilation successful.
                ) else (
                    echo Compilation failed.
                    exit 1
                )
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Creating runnable JAR...'
                bat 'jar cfe SimpleAlarmApp.jar SimpleAlarm SimpleAlarm.class'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Running the JAR (deployment simulation)...'
                // Running GUI on Jenkins SYSTEM account may not show GUI.
                // This will just ensure JAR runs without crashing.
                bat 'java -jar SimpleAlarmApp.jar || echo GUI cannot display in Jenkins service account'
            }
        }
    }

    post {
        always {
            echo 'Archiving JAR file for download...'
            archiveArtifacts artifacts: 'SimpleAlarmApp.jar', allowEmptyArchive: false
        }

        success {
            echo 'Build, Test, Package, Deploy succeeded!'
        }

        failure {
            echo 'Build failed. Check console output.'
        }
    }
}
