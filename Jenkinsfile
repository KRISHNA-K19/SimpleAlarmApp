pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clone your GitHub repo
                git branch: 'main', url: 'https://github.com/KRISHNA-K19/SimpleAlarmApp.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java program...'
                bat 'javac SimpleAlarm.java'
            }
        }

        stage('Run GUI App') {
            steps {
                echo 'Launching GUI application...'
                bat 'start java SimpleAlarm'
            }
        }
    }
}
