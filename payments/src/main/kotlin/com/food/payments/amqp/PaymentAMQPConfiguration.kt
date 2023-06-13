package com.food.payments.amqp


import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentAMQPConfiguration {

//    @Bean  --- não precisa mais criar a fila, já que vai criar as exchanges
//    fun queueCreate(): Queue {
//        return Queue("pagamento.concluido", false)
//    }

    @Bean
    fun createRabbitAdmin(connection: ConnectionFactory): RabbitAdmin {
        return RabbitAdmin(connection)
    }

    @Bean
    fun adminInitialize(rabbitAdmin: RabbitAdmin): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { rabbitAdmin.initialize() }
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

//    @Bean
//    fun rabbitTemplateConverter(
//        connectionFactory: ConnectionFactory
//    ): MessageConverter {
//        val rabbitTemplate = RabbitTemplate(connectionFactory).messageConverter
//        return rabbitTemplate
//
//    }

    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange("pagamento.ex")
    }
}