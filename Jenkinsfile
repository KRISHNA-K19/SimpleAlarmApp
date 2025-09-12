pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Pull the code from GitHub
                git 'https://github.com/yourusername/SimpleAlarmApp.git'
            }
        }
        stage('Build') {
            steps {
                // Compile Java files and create a JAR
                sh 'javac src/SimpleAlarm.java -d bin'
                sh 'jar cfe SimpleAlarm.jar SimpleAlarm -C bin .'
            }
        }
        stage('Archive') {
            steps {
                // Save the JAR as build artifact
                archiveArtifacts 'SimpleAlarm.jar'
            }
        }
    }
}
