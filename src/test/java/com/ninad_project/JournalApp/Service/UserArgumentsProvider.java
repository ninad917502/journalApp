package com.ninad_project.JournalApp.Service;

import com.ninad_project.JournalApp.Entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;



import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        return Stream.of(
                Arguments.of(User.builder().username("Raj").password("Raj").build()),
                Arguments.of(User.builder().username("Atul").password("").build())
        );
    }
}
