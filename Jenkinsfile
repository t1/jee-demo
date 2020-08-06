@Library('jenkins_shared_libraries') _

pipeline {
    agent any
    tools {
        jdk 'AdoptOpenJDK 11'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
    }

    stages {
        stage('Package') {
            steps {
                // Run the maven build
                script {
                    mvn "clean package"
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archive 'target*//*.jar'
                }
            }
        }
    }
}

void mvn(String command) {
    def mavenHome = tool('Maven 3.6.3')
    sh "'${mavenHome}/bin/mvn' ${command}"
}
