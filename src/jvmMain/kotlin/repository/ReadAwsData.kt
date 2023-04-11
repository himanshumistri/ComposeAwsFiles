package repository

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import kotlinx.coroutines.CoroutineScope

object ReadAwsData {


    suspend fun listBucketObjects(
        bucketName: String
    ){

        val request = ListObjectsRequest {
            bucket = bucketName
        }

        val cred = object : CredentialsProvider {
            override suspend fun getCredentials(): Credentials {

                return Credentials("test","test")
            }
        }

        S3Client {region="ap-south-1";credentialsProvider=cred}.use { s3 ->
            val response = s3.listObjects(request)

            println("AWS Read Status is ${response.contents?.size} More is ${response.name}")
        }

    }

}