package org.acme

import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import jakarta.enterprise.context.ApplicationScoped
import org.junit.jupiter.api.Test

class ConcreteFoo

abstract class AbstractService<E> {
    open fun findOneEntity(id: Long): E = TODO()
    open fun doNothing() = Unit
}

@ApplicationScoped
class FooBarService : AbstractService<ConcreteFoo>()

@QuarkusTest
class FooBarServiceTest {

    @InjectMock
    lateinit var fooBarService: FooBarService

    @Test
    fun foo() {
        every { fooBarService.doNothing() } returns Unit // Works
        every { fooBarService.findOneEntity(any()) } returns ConcreteFoo() // java.lang.ClassNotFoundException: ConcreteFoo
    }

}