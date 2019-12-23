package de.outfittery.customerreportingworker.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.elasticsearch.core.EntityMapper
import org.springframework.stereotype.Component

@Component
class CustomEntityMapper(private val objectMapper: ObjectMapper) : EntityMapper {

    companion object {
        private val mapTypeReference = object : TypeReference<MutableMap<String, Any>>() {}
    }

    override fun <T : Any?> mapToObject(source: String?, clazz: Class<T>?): T = objectMapper.readValue(source, clazz)

    override fun mapObject(source: Any?): MutableMap<String, Any> = objectMapper.readValue(source?.toString(), mapTypeReference)

    override fun mapToString(`object`: Any?): String = objectMapper.writeValueAsString(`object`)

    override fun <T : Any?> readObject(source: MutableMap<String, Any>?, targetType: Class<T>?): T? = objectMapper.readValue(objectMapper.writeValueAsString(source), targetType)
}