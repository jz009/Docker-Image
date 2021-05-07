pipeline{
    agent any
    stages{
        stage("Build"){
            agent{docker {image 'maven:3.3.3'} }
            steps{
                git 'https://github.com/jz009/Docker-Image.git/'
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }
        stage("Deploy"){
            steps{
                sh "docker build -t jav ."
                sh "docker run -d -p 8081:8080 jav sh"
            }
        }
    }
}