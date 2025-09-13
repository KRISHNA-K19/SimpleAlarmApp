pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/KRISHNA-K19/SimpleAlarmApp.git'
            }
        }
        stage('Build') {
            steps {
                bat 'javac Main.java'
            }
        }
        stage('Package') {
            steps {
                bat 'jar cfe SimpleAlarmApp.jar Main Main.class'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'SimpleAlarmApp.jar', fingerprint: true
            }
        }
    }
}
