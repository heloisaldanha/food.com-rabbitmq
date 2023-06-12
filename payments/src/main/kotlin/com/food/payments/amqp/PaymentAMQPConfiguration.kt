package com.food.payments.amqp


import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentAMQPConfiguration {

    @Bean
    fun queueCreate(): Queue {
        return Queue("pagamento.concluido", false)
    }

    @Bean
    fun createRabbitAdmin(connection: ConnectionFactory): RabbitAdmin {
        return RabbitAdmin(connection)
    }

    @Bean
    fun adminInitialize(rabbitAdmin: RabbitAdmin): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { rabbitAdmin.initialize() }
    }
}