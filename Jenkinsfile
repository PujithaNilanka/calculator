pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestCoverageVerification"
                sh "./gradlew jacocoTestReport"
                publishHTML(target: [
                    reportDir: 'build/reports/jacoco/test/html/',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
            }
        }
        stage("Static code analysis") {
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
        stage("Package"){
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build"){
            steps {
                sh "docker build -t pujitha/calculator . "
            }
        }
        stage("Deploy to staging"){
            steps {
                // This is docker based service deployment. Subsequent line replaced this with docker-compose
                // sh "docker run -d --rm -p 8765:8080 --name calculator pujitha/calculator"
                sh "docker-compose up -d"
            }
        }
        stage("Acceptance test - shell based"){
            steps {
                sleep 60
                sh "chmod +x ./acceptance_test.sh"
                sh "./acceptance_test.sh"
            }
        }

    }
    post {
        always {
            // This is docker based service deployment. Subsequent line replaced this with docker-compose
            // sh "docker stop calculator"
            sh "docker-compose down"
        }
    }
}