pipeline{
    agent any
    tools{
        maven 'maven3.9.8'
    }
    stages{
        stage('pulling code from repository'){
            steps{
                git branch: 'main', url: 'https://github.com/tanviparab786/java-backend.git'
            }
        }
        stage('build'){
            steps{
                  sh 'mvn clean package'
            }
             
        }
        
    }
}