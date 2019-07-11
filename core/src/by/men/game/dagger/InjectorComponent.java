package by.men.game.dagger;

import by.mkwt.engine.dagger.Injector;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MainModule.class, InjectorModule.class})
public interface InjectorComponent extends Injector {



}
