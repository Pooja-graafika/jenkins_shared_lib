// def call(String project, String ImageTag, String hubUser){
    
//     sh """   
//      trivy image ${hubUser}/${project}:latest > scan.txt
//      cat scan.txt
//     """
// }

def call(String aws_account_id, String region, String ecr_repoName){
    
    sh """
    trivy image -f json -o scan.json ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest 
    cat scan.json
                        def vulnerabilities = readJSON(text: scan.json)
                    if (vulnerabilities.length > 0) {
                        // If vulnerabilities are found, fail the pipeline
                        error "Trivy scan found ${vulnerabilities.length} vulnerabilities"
						
						
						}
    """
}
