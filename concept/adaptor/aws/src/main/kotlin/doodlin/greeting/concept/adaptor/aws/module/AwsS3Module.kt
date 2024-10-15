package doodlin.greeting.concept.adaptor.aws.module

import doodlin.greeting.concept.adaptor.aws.S3Path
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.http.HttpStatusCode
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import java.io.InputStream

internal class AwsS3Module(
    private val s3Client: S3Client,
) {

    /**
     * 이관 전 코드는 exception 발생시, emptyMap()을 반환하는 구조였음
     * 필요에 따라 상위 코드에서 처리하는 것을 잊지 말아야 함
     */
    fun getObjectTags(
        s3Path: S3Path,
    ): Map<String, String> {
        return s3Client.getObjectTagging {
            it.bucket(s3Path.bucket)
            it.key(s3Path.key)
        }.tagSet().associate { it.key() to it.value() }
    }

    /**
     * 이관 전 코드는 exception 발생시, ignore 처리하는 구조였음
     * 필요에 따라 상위 코드에서 처리하는 것을 잊지 말아야 함
     */
    fun copyObject(
        source: S3Path,
        destination: S3Path,
    ) {
        s3Client.copyObject(
            CopyObjectRequest.builder()
                .sourceBucket(source.bucket)
                .sourceKey(source.key)
                .destinationBucket(destination.bucket)
                .destinationKey(destination.key)
                .build()
        )
    }

    /**
     * 이관 전 코드는 exception 발생시, ignore 처리하는 구조였음
     * 필요에 따라 상위 코드에서 처리하는 것을 잊지 말아야 함
     */
    fun deleteObject(
        s3Path: S3Path,
    ) {
        s3Client.deleteObject(
            DeleteObjectRequest.builder()
                .bucket(s3Path.bucket)
                .key(s3Path.key)
                .build()
        )
    }

    /**
     * 이관 전 코드는 exception 발생시, ignore 처리하는 구조였음
     * 필요에 따라 상위 코드에서 처리하는 것을 잊지 말아야 함
     */
    fun renameObject(
        destination: S3Path,
        source: S3Path,
    ) {
        copyObject(destination = destination, source = source)
        deleteObject(s3Path = source)
    }

    /**
     * 이관 전 코드는 exception 발생시, ignore 처리하는 구조였음
     * 필요에 따라 상위 코드에서 처리하는 것을 잊지 말아야 함
     */
    fun existObject(s3Path: S3Path): Boolean {
        return try {
            s3Client.headObject(
                HeadObjectRequest.builder()
                    .bucket(s3Path.bucket)
                    .key(s3Path.key)
                    .build()
            )
            true
        } catch (s3Exception: S3Exception) {
            when (s3Exception.statusCode()) {
                HttpStatusCode.NOT_FOUND -> false
                else -> throw s3Exception
            }
        }
    }

    fun uploadObject(
        s3Path: S3Path,
        byteArray: ByteArray,
    ) {
        uploadObject(s3Path = s3Path, requestBody = RequestBody.fromBytes(byteArray))
    }

    fun uploadObject(
        s3Path: S3Path,
        inputStream: InputStream,
    ) {
        uploadObject(s3Path = s3Path, RequestBody.fromBytes(inputStream.readBytes()))
    }

    private fun uploadObject(
        s3Path: S3Path,
        requestBody: RequestBody,
    ) {
        s3Client.putObject(
            PutObjectRequest.builder().bucket(s3Path.bucket).key(s3Path.key).build(),
            requestBody
        )
    }

    fun getObjectStream(s3Path: S3Path): InputStream {
        return s3Client.getObject {
            it.bucket(s3Path.bucket)
            it.key(s3Path.key)
        }
    }
}
