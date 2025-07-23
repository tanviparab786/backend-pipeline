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
        stage('deploy'){
            steps{

                sshagent(['tomcat-server']) {
                 sh 'sudo scp target/*war azureuser@10.1.0.5:/home/azureuser/'
                 sh 'ssh -o StrictHostKeyChecking=no azureuser@10.1.0.5 "sudo mv demo-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/test.war"'

                }
                
                
            }
        }
        
    }
}