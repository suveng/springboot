pipeline {
  agent any
  triggers {
        githubPush()
  }
  stages {
    stage('停机') {
      steps {
        sh label: '', script: '''#!/bin/bash -ilex
        java -version
        mvn -version
        PID=$(cat /etc/xiaobo/suveng/springboot.pid)
        kill $PID
        jps
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
          sh label: '', script: '''#!/bin/bash -ilex
          jps
          mv target/*.jar /etc/xiaobo/suveng/app/springboot/
          JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /etc/xiaobo/suveng/app/springboot/*.jar >  /etc/xiaobo/suveng/log/springboot.log &
          echo $! > /etc/xiaobo/suveng/springboot.pid
          PID=$(cat /etc/xiaobo/suveng/springboot.pid)
          echo $PID
          jps
          '''
        }
    }
  }
}
