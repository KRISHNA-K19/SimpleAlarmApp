pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = '5ef323fb-9b23-4058-8d55-6d9337f15427' // Jenkins GitHub credential ID
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
                bat "if not exist ${JAVA_FILE} (echo ERROR: ${JAVA_FILE} not found! & exit /b 1)"
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java program...'
                bat "javac ${JAVA_FILE}"
                bat "if not exist ${CLASS_FILE} (echo ERROR: Compilation failed! & exit /b 1)"
            }
        }

       stage('Package') {
    steps {
        echo 'Creating runnable JAR...'
        // Include all .class files
        bat "jar cfe ${JAR_FILE} SimpleAlarm *.class"
        bat "if not exist ${JAR_FILE} (echo ERROR: JAR creation failed! & exit /b 1)"
    }
}


    post {
        always {
            echo 'Archiving JAR file for download...'
            archiveArtifacts artifacts: "${JAR_FILE}", allowEmptyArchive: false
        }
stage('Clean Workspace') {
    steps {
        echo 'Cleaning workspace before build...'
        bat 'if exist * rmdir /s /q *'
    }
}

        success {
            echo 'Build, Test, Package, Deploy succeeded!'
        }

        failure {
            echo 'Build failed. Check console output.'
        }
    }
}

