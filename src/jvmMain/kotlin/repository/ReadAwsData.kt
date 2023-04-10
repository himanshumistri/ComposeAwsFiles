package repository

import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import kotlinx.coroutines.CoroutineScope

object ReadAwsData {


    suspend fun listBucketObjects(
        bucketName: String
    ){

        val request = ListObjectsRequest {
            bucket = bucketName
        }

    }

}