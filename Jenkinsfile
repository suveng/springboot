pipeline {
  agent any
  triggers {
        githubPush()
  }
  stages {
    stage('准备环境') {
      steps {
        sh -ilex 'source /etc/profile'
        sh -ilex 'java -version'
        sh -ilex 'mvn -version'
      }
    }
    stage('构建') {
      steps {
        sh 'mvn clean'
        sh 'mvn insatll -Dmaven.test.skip=true'
        sh 'mvn package -Dmaven.test.skip=true'
      }
    }
    stage('测试') {
      steps {
        sh 'echo "########运行测试脚本########"'
      }
    }
    stage('部署') {
      steps {
        sh 'echo "########运行部署脚本########"'
        sh 'java -jar target/*.jar'
        sh 'echo "########部署成功########"'
      }
    }
  }
}
