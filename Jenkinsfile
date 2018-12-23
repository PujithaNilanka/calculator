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
        stage("Acceptance test - shell/docker-compose based"){
            steps {
                // These 3 lines are for the script based acceptance tests replaced with docker compose tests
                // sleep 60
                // sh "chmod +x ./acceptance_test.sh"
                // sh "./acceptance_test.sh"
                sh "docker-compose -f docker-compose.yml -f acceptance/docker-compose-acceptance.yml build test"
                sh "docker-compose -f docker-compose.yml -f acceptance/docker-compose-acceptance.yml -p acceptance up -d"
                sh 'test $(docker wait acceptance_test_1) -eq 0'
            }
        }
        stage("Acceptance test - cucumber based"){
             steps {
                sh "export CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)"
                sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:$CALCULATOR_PORT"
             }
        }
    }
    post {
        always {
            // Stop the docker based service deployment.
            // sh "docker stop calculator"
            // Stop the docker-compose instances.
            // sh "docker-compose down"
            // Stop and remove docker-compose environment
            sh "docker-compose -f docker-compose.yml -f acceptance/docker-compose-acceptance.yml -p acceptance down"
        }
    }
}
