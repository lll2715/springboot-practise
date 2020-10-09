package com.example.springbootpractise;

import com.example.springbootpractise.mq.MqConstant;
import com.example.springbootpractise.mq.MqReceiver;
import com.example.springbootpractise.redis.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
 * @EnableScheduling 开启定时任务配置
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class SpringbootPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPractiseApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
		RedisMessageListenerContainer messageListenerContainer = new RedisMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory);
		messageListenerContainer.addMessageListener(listenerAdapter, new PatternTopic("chat"));
		return  messageListenerContainer;
	}


	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver){
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}


	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory){
		return new StringRedisTemplate(connectionFactory);
	}


	@Bean
	Queue queue() {
		return new Queue(MqConstant.queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(MqConstant.topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer containerMq(ConnectionFactory connectionFactory,
											 org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter listenerAdapterMq) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(MqConstant.queueName);
		container.setMessageListener(listenerAdapterMq);
		return container;
	}

	@Bean
	org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter listenerAdapterMq(MqReceiver mqReceiver) {
		return new org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter(mqReceiver, "consumeMsg");
	}



}
