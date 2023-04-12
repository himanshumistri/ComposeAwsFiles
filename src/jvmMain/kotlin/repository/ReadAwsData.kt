package repository

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import java.io.ByteArrayOutputStream
import java.io.PrintStream


object ReadAwsData {


    suspend fun listBucketObjects(
        bucketName: String ,onResult:(String) -> Unit
    ){

        val request = ListObjectsRequest {
            bucket = bucketName
        }

        onResult.invoke("Request Created \n")
        val cred = object : CredentialsProvider {
            override suspend fun getCredentials(): Credentials {

                return Credentials("test","test")
            }
        }

        try {
            S3Client {region="ap-south-1";credentialsProvider=cred}.use { s3 ->
                val response = s3.listObjects(request)
                onResult.invoke("AWS Read Status is ${response.contents?.size} More is ${response.name} \n")
                println("AWS Read Status is ${response.contents?.size} More is ${response.name}")
            }
        }catch (exception: Exception){
            val out = ByteArrayOutputStream()
            exception.printStackTrace(PrintStream(out))
            val str = String(out.toByteArray())
            exception.printStackTrace()
            onResult.invoke("\n $str")
        }


    }

}