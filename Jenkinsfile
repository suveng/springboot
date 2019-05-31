pipeline {
  agent any
  triggers {
        githubPush()
  }
  stages {
    stage('准备环境') {
      steps {
        sh label: '', script: '''#!/bin/bash -ilex
        java -version
        mvn -version
        '''
      }

    }
    stage('构建') {
      steps {
        sh label: '', script: '''#!/bin/bash -ilex
        mvn clean
        mvn install
        mvn package
        '''
      }
    }
    stage('测试') {
      steps {
        sh 'echo "########运行测试脚本########"'
      }
    }
    stage('部署') {
      steps {
      withEnv(['BUILD_ID=dontKillMe']) {
        sh label: '', script: '''#!/bin/bash -ilex
                jps
                nohup java -jar target/*.jar > springboot.log &
                jps
                '''
        }
      }

    }
  }
}
