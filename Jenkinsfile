pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = '5ef323fb-9b23-4058-8d55-6d9337f15427' // Your Jenkins GitHub credential ID
        JAVA_FILE = 'SimpleAlarm.java'
        CLASS_FILE = 'SimpleAlarm.class'
        JAR_FILE = 'SimpleAlarmApp.jar'
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

        stage('Prepare Workspace') {
            steps {
                echo 'Checking workspace and files...'
                bat 'cd'
                bat 'dir'
                bat """
                if not exist ${JAVA_FILE} (
                    echo ERROR: ${JAVA_FILE} not found!
                    exit 1
                )
                """
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java program...'
                bat "javac ${JAVA_FILE}"
                bat """
                if not exist ${CLASS_FILE} (
                    echo ERROR: Compilation failed!
                    exit 1
                )
                """
            }
        }

        stage('Package') {
            steps {
                echo 'Creating runnable JAR...'
                bat "jar cfe ${JAR_FILE} SimpleAlarm ${CLASS_FILE}"
                bat """
                if not exist ${JAR_FILE} (
                    echo ERROR: JAR creation failed!
                    exit 1
                )
                """
            }
        }

        stage('Deploy / Test') {
            steps {
                echo 'Testing JAR execution (GUI may not display in Jenkins)...'
                bat "java -jar ${JAR_FILE} || echo GUI cannot display in Jenkins service account"
            }
        }
    }

    post {
        always {
            echo 'Archiving JAR file for download...'
            archiveArtifacts artifacts: "${JAR_FILE}", allowEmptyArchive: false
        }

        success {
            echo 'Build, Test, Package, Deploy succeeded!'
        }

        failure {
            echo 'Build failed. Check console output.'
        }
    }
}
