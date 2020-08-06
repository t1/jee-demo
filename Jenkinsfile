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
                    def mvnHome = getMavenHome()
                    sh "'${mvnHome}/bin/mvn' clean package"
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archive 'target*//*.jar'
                }
            }
        }
    }
}

def getMavenHome() {
    return tool('Maven 3.6.3')
}

def getDevVersion() {
    def gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    def versionNumber
    if (gitCommit == null) {
        versionNumber = env.BUILD_NUMBER
    } else {
        versionNumber = gitCommit.take(8)
    }
    print 'build  versions...'
    print versionNumber
    return versionNumber
}
