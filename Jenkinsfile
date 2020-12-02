pipeline {
     agent any
     stages {
        stage("Run Jar") {
            steps {
                sh "sh run.sh"
                sh "sleep 4"
                sh "sudo cp *.jar /"
                 sh "cd /"
                sh "sudo /usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar projeto-pandora-0.0.1-SNAPSHOT.jar --server.port=8081 &"
            }
        }
    }
}
