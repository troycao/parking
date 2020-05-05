package com.troy.parking.core.manager.config;

import com.troy.parking.common.constants.RabbitMQProperties;
import com.troy.parking.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * @author troy
 * @date 2020/3/26
 **/
@Slf4j
@Configuration
public class RabbitMQConfig {
    private final static Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
    @Bean
    public Queue GateNotifyQueue() {
        //true 是否持久
        return new Queue(RabbitMQProperties.QUEUE_GATE_NOTIFY,true);
    }
    @Bean(RabbitMQProperties.TEMPLATE_DIRECT)
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter);
        template.setExchange(RabbitMQProperties.DIRECT_EXCHANGE);
        return template;
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        factory.setAdviceChain(createRetry());
        //重试完的队列 不再重新投递 直接丢弃消息
        factory.setDefaultRequeueRejected(Boolean.FALSE);
        return factory;
    }
    private RetryOperationsInterceptor createRetry() {
      /*  RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.registerListener(new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
                // 第一次重试调用
                //log.info("第一次重试调用");
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                // 最后一次重试会调用
                log.info("最后一次重试会调用");

            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                // 每次重试失败后都会调用
                log.info("每次重试失败后都会调用,{}", JsonUtil.toJsonString(context.attributeNames()));
            }
        });
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(5));
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        //重试间隔3秒
        exponentialBackOffPolicy.setInitialInterval(3000L);
        // 倍数 1 每次重试 下次间隔时间=当前间隔时间*倍数
        exponentialBackOffPolicy.setMultiplier(1D);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);*/

        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
                .backOffOptions(3000L, 1D, 30000L).recoverer((Object[] var1, Throwable var2) -> {
                    Object obj = jackson2JsonMessageConverter.fromMessage((Message) var1[1]);
                    log.error("全部重试失败:" + JsonUtil.toJsonString(obj),var2);
                    return var1;
                }).build();
    }
    /**
     * Direct交换机
     * @return
     */
    @Bean
    DirectExchange DirectExchange() {
        return new DirectExchange(RabbitMQProperties.DIRECT_EXCHANGE);
    }
    /**
     * //绑定  将队列和交换机绑定, 并设置用于匹配键：DirectRouting
     */
    @Bean
    Binding gateNotifyRouting(Queue gateNotifyQueue) {
        return BindingBuilder.bind(gateNotifyQueue).to(DirectExchange()).with(RabbitMQProperties.ROUTING_KEY_GATE_NOTIFY);
    }
}
