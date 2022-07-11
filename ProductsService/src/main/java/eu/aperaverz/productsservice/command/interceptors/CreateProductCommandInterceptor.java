package eu.aperaverz.productsservice.command.interceptors;

import eu.aperaverz.productsservice.command.CreateProductCommand;
import eu.aperaverz.productsservice.core.data.ProductLookupEntity;
import eu.aperaverz.productsservice.core.data.ProductLookupRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    @SuppressWarnings("unused")
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {

            log.info("Intercepted command: " + command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                ProductLookupEntity productLookupEntity = productLookupRepository
                        .findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle())
                        .orElseThrow(() -> {
                            throw new IllegalStateException(
                                    String.format("Product with productId %s of title %s already exists",
                                            createProductCommand.getProductId(),
                                            createProductCommand.getTitle()));
                        });
            }

            return command;
        };
    }
}
