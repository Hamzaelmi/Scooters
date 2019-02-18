package com.elmilabs.scooters.di;

import com.elmilabs.scooters.scooterListing.ScooterListFragment;
import dagger.Component;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

@Singleton
@Component(modules={MainModule.class})
public interface FragmentComponent {
    void inject(@NotNull ScooterListFragment scooterListFragment);
}