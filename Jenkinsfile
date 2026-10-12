pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Retrieving source code from Git'
                checkout scm
            }
        }

        stage('Build') {
    steps {
        bat 'mvn -B -DskipTests clean compile'
    }
}

        stage('Test') {
    steps {
        bat 'mvn -B test'
    }
}

        stage('Package') {
            steps {
                bat 'mvn -B -DskipTests package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t lab13-java-app:latest .'
            }
        }

        stage('Verify Docker Image') {
            steps {
                bat 'docker image inspect lab13-java-app:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Open Console Output and check the first failing stage.'
        }
    }
}
