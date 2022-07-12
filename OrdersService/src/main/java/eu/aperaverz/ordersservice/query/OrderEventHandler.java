package eu.aperaverz.ordersservice.query;

import eu.aperaverz.ordersservice.core.data.OrderEntity;
import eu.aperaverz.ordersservice.core.data.OrdersRepository;
import eu.aperaverz.ordersservice.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderEventHandler {
    private final OrdersRepository ordersRepository;

    public OrderEventHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();

        BeanUtils.copyProperties(event, orderEntity);

        ordersRepository.save(orderEntity);
    }
}
