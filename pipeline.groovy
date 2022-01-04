@Library('pipeline_lib') _

pipeline{
    agent any
    parameters {
        string(name: 'release_number', defaultValue: '0.0.0.1', description: 'Release Number for the Application')
    }
    stages{
        stage("Initialize Pipeline"){
            steps{
                sayHello "Dave"
            }
        }
        stage("Build"){
            steps{
                build_mvn([tool: "mvn", args: "-Drelease_number=${params.release_number}"])
            }
        }
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}