package de.outfittery.customerreportingworker.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "task")
data class StylistTask(
        @Id val taskId: String? = null,
        val assignee: String? = null,
        val taskType: String? = null,
        val taskActions: List<TaskAction> = listOf(),
        val processInstanceId: String? = null,
        val processDefinitionId: String? = null,
        var customerId: String? = null,
        val variables: Map<String, Any?> = mapOf(),
        val error: String? = null
) {
    override fun toString(): String {
        return "StylistTask(assignee=$assignee, taskId=$taskId, taskType=$taskType)"
    }


}

data class TaskAction(
        val actionType: String? = null,
        val actionValue: String? = null
)