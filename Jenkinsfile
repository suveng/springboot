pipeline {
  agent any
  triggers {
        githubPush()
  }
  stages {
    stage('停机') {
      steps {
        sh label: '', script: '''#!/bin/bash -ile

        echo '查看java 版本'
        java -version

        echo '查看maven版本'
        mvn -version

        echo '获取pid'
        PID=$(cat /etc/xiaobo/suveng/springboot.pid)
        echo "pid进程: $PID"


        RES=$(kill $PID)
        echo "KILL RES : $RES"

        echo '判断kill是否成功'
        if [ !$RES ]
        then
          echo '0,成功杀死进程'
        else
          echo '1,kill失败'
          echo '退出-1'
          exit 1
        fi

        echo '查看进程'
        jps
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
