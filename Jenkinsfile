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
        stage('Build with unit testing') {
            steps {
                // Run the maven build
                script {
                    // Get the Maven tool.
                    // ** NOTE: This 'M3' Maven tool must be configured
                    // **       in the global configuration.
                    echo 'Pulling...' + env.BRANCH_NAME
                    def mvnHome = getMavenHome()
                    def targetVersion = getDevVersion()
                    print 'target build version...'
                    print targetVersion
                    sh "'${mvnHome}/bin/mvn' -Dintegration-tests.skip=true -Dbuild.number=${targetVersion} clean package ${KEYSTORE_GLOBALS}"
                    // execute the unit testing and collect the reports
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
