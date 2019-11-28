#!/usr/bin/env groovy
import java.text.SimpleDateFormat

properties([
        [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
        pipelineTriggers([])
])

if ("${project_type}" == "java") {
    node('openjdk-8') {

        APP_TYPE = "simulator-java"

        // Clean workspace
        stage('Clean workspace') {
            cleanWs()
        }

        stage('Checkout') {
            checkout scm
            // Get commit ID
            sh "git rev-parse HEAD | tail -c 8 > var_short_commit-id"
            // Stash vars
            stash includes: "var_*", name: "var-stash"

            unstash "var-stash"
            def short_commit_id = readFile('var_short_commit-id').trim()
            println "Git branch = ${BRANCH_NAME}, Git commit id: ${short_commit_id}"

        }

        def target_env = "dev"
        def jbehave_story = "GenerateTestFiles"
        def offer_count = 10
        def offer_allocation_per_member = 10
        def ff_user_count = 100
        def date_generated_filename = new SimpleDateFormat('yyyy-MM-dd').parse("2019-06-14").format('yyyyMMdd')
        def date_generated_bucketname = new SimpleDateFormat('yyyy-MM-dd').parse("2019-06-14").format('yyyy-MM-dd')
        def account = ''

        if ("${target_env}" == "dev") {
            account = "762508870528"
        }

        stage("Test") {
            sh "echo 'Run Static Code Analysis with a tool like Sonar or CodeClimate'"
            sh "echo 'Run Unit tests and Integration Tests'"
        }

        stage('Build') {
            println "mvn -q clean install -Djbehave.story.name=${jbehave_story} -Dspring.profiles.active=${target_env} -Ddummy.Offer.Count=${offer_count} -DOffer.per.Member=${offer_allocation_per_member} -Ddummy.FFUser.Count=${ff_user_count} -Dfile.Run.Date=${date_generated}"
            sh "mvn clean install -X -Djbehave.story.name=${jbehave_story} -Dspring.profiles.active=${target_env} -Ddummy.Offer.Count=${offer_count} -DOffer.per.Member=${offer_allocation_per_member} -Ddummy.FFUser.Count=${ff_user_count} -Dfile.Run.Date=${date_generated}"
        }

        if ("${BRANCH_NAME}" == "develop" || "${BRANCH_NAME}" =~ "feature/*") {
            stage("Release to DEV") {
                sh "echo 'Release the artifact to dev'"
            }
        }

        if ("${BRANCH_NAME}" == "master") {
            stage("Release to PRD") {
                timeout(time: 30, unit: 'MINUTES') {
                    input message: "Are you sure you want to release into Production Envionment?",
                    ok: 'Ok to release'
                }
                try{
                    sh "echo 'Release the artifact to dev'"
                }
                catch(err) {
                    def user = err.getCauses()[0].getUser()
                    if('SYSTEM' == user.toString()) { //timeout
                        currentBuild.result = "SUCCESS"
                    }
                }
            }
        }
    }
}
