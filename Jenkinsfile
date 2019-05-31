pipeline {
  agent any
  stages {
    stage('停机') {
      steps {
        sh label: '', script: '''#!/bin/bash -ile

        echo '查看java 版本'
        java -version

        echo '查看maven版本'
        mvn -version

        echo '获取pid'
        PID=$(jps | grep server | awk -F ' ' '{ print  $1 }')
        echo "pid进程: $PID"

        if [ -d /proc/$PID];then
            echo "$PID进程存在,继续执行"
        else
            echo '$PID不存在,退出,-1'
            exit -1
        fi

        kill $PID

        '''
      }

    }
    stage('构建') {
      steps {
        sh label: '', script: '''#!/bin/bash -ile
        mvn clean
        mvn install -Dmaven.test.skip=true
        mvn package -Dmaven.test.skip=true
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
          sh label: '', script: '''#!/bin/bash -ile
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
