package com.skeet.rabbitmq.config;

import com.skeet.rabbitmq.util.ReflectionUtil;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * <p>
 * description: 消息队列配置类
 *
 * @author xubo
 * create: 2018-11-07 19:33
 */
public abstract class BaseRabbitConfig {

    public static final int FUND_MESSAGE_THREAD_SIZE = 50;
    public static final String TRACE_EXCHANGE_NAME = "trace-logging-exchange";

    public static final String DEFAULT_QT_EXCHANGE = "qt-mq-exchange-default";

    @Resource
    private ConnectionFactory connectionFactory;

    private RabbitAdmin rabbitAdmin;

    @Getter
    private DirectExchange defaultDirectExchange;

    @PostConstruct
    public void init() {
        List<String> bindingQueueList = buildMqBindingQueueList();
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        defaultDirectExchange = declareDefaultDirectExchange();
        declareBindingQueue(defaultDirectExchange, bindingQueueList);
    }

    /**
     * 构建需要注册的队列集合
     */
    private List<String> buildMqBindingQueueList() {
        return ReflectionUtil.getFieldValues(getQueueConstantClass());
    }

    protected abstract Class<?> getQueueConstantClass();

    /**
     * 声明默认交换机
     */
    private DirectExchange declareDefaultDirectExchange() {
        DirectExchange directExchange = new DirectExchange(DEFAULT_QT_EXCHANGE);
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    /**
     * 声明绑定的队列
     */
    private void declareBindingQueue(DirectExchange exchange, List<String> bindingQueueList) {
        if (CollectionUtils.isEmpty(bindingQueueList)) {
            return;
        }
        for (String queueName : bindingQueueList) {
            Queue queue = declareQueue(queueName);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(queueName));
        }
    }

    /**
     * 声明队列
     */
    private Queue declareQueue(String queueName) {
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("confirm call back: data - " + correlationData);
            System.out.println("confirm call back: ack - " + ack);
            System.out.println("confirm call back: cause - " + cause);
        });

        template.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("return call back: message - " + message);
            System.out.println("return call back: replyCode - " + replyCode);
            System.out.println("return call back: replyText - " + replyText);
            System.out.println("return call back: exchange - " + exchange);
            System.out.println("return call back: routingKey - " + routingKey);
        });
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(10);
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

//    @Bean
//    @Qualifier("fundListenerContainerFactor")
//    public SimpleRabbitListenerContainerFactory fundListenerContainerFactor(ConnectionFactory connectionFactory) {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(FUND_MESSAGE_THREAD_SIZE);
//        executor.setMaxPoolSize(FUND_MESSAGE_THREAD_SIZE);
//        executor.setQueueCapacity(FUND_MESSAGE_THREAD_SIZE);
//        executor.setKeepAliveSeconds(60);
//        executor.setThreadNamePrefix("message-fund-");
//        executor.setRejectedExecutionHandler((r, exe) -> {
//            throw new RejectedExecutionException("fund message thread pool is full: " + r.toString() + "; rejected from: " + exe.toString());
//        });
//        executor.initialize();
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setPrefetchCount(20);
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setTaskExecutor(executor);
//        return factory;
//
//    }
}
